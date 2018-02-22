package four.pda.ui.article.list;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.View;

import com.crashlytics.android.Crashlytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import four.pda.R;
import four.pda.client.model.ListArticle;
import four.pda.ui.LoadResult;

/**
 * Created by asavinova on 24/05/16.
 */
public class ListCallbacks implements LoaderManager.LoaderCallbacks<LoadResult<List<ListArticle>>> {

	private static final Logger L = LoggerFactory.getLogger(ListCallbacks.class);

	private ListFragment fragment;

	public ListCallbacks(ListFragment fragment) {
		this.fragment = fragment;
	}

	@Override
	public AsyncTaskLoader<LoadResult<List<ListArticle>>> onCreateLoader(int id, final Bundle args) {
		return new AsyncTaskLoader<LoadResult<List<ListArticle>>>(ListCallbacks.this.fragment.getActivity()) {
			@Override
			public LoadResult<List<ListArticle>> loadInBackground() {
				try {
					List<ListArticle> articles = fragment.client.getArticles(fragment.category, fragment.page);
					return new LoadResult<>(articles);
				} catch (Exception e) {
					String format = "Can't load articles list for category [%s] and page [%d]";
					String message = String.format(format, fragment.category.name(), fragment.page);
					L.error(message, e);
					Crashlytics.logException(new RuntimeException(message, e));
					return new LoadResult<>(e);
				}
			}
		};
	}

	@Override
	public void onLoadFinished(Loader<LoadResult<List<ListArticle>>> loader, LoadResult<List<ListArticle>> result) {

		fragment.refresh.setRefreshing(false);

		if (result.isError()) {

			View.OnClickListener retryListener = v -> fragment.loadData();

			int itemCount = fragment.adapter.getItemCount();

			if (itemCount == 0) {
				fragment.upButton.setVisibility(View.GONE);
				fragment.supportView.showError(fragment.getString(R.string.article_list_network_error), retryListener);
				return;
			}

			Snackbar
					.make(fragment.container, R.string.article_list_network_error, Snackbar.LENGTH_INDEFINITE)
					.setAction(R.string.retry_button, retryListener)
					.show();

			return;

		}

		boolean needClearData = fragment.page == 1;
		fragment.dao.setArticles(result.getData(), fragment.category, needClearData);

		Cursor cursor = fragment.dao.getArticleCursor(fragment.category);
		fragment.adapter.swapCursor(cursor);
		fragment.adapter.notifyDataSetChanged();

		fragment.supportView.hide();
		fragment.upButton.setVisibility(View.VISIBLE);

		fragment.page++;

	}

	@Override
	public void onLoaderReset(Loader<LoadResult<List<ListArticle>>> loader) {
	}

}
