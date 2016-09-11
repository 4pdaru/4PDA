package four.pda.ui.article.comments.add;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;

import four.pda.R;
import four.pda.client.model.CommentsContainer;
import four.pda.ui.LoadResult;

/**
 * Created by asavinova on 16/03/16.
 */
public class AddCommentCallbacks implements LoaderManager.LoaderCallbacks<LoadResult<CommentsContainer>> {

	private AddCommentDialog fragment;

	public AddCommentCallbacks(AddCommentDialog fragment) {
		this.fragment = fragment;
	}

	@Override
	public AddCommentLoader onCreateLoader(int id, Bundle args) {
		return new AddCommentLoader(fragment);
	}

	@Override
	public void onLoadFinished(Loader<LoadResult<CommentsContainer>> loader, LoadResult<CommentsContainer> result) {
		fragment.supportView.hide();

		if (result.getException() != null) {
			fragment.supportView.showError(fragment.getString(R.string.add_comment_network_error), new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					fragment.addComment();
				}
			});
			return;
		}

		fragment.updateComments(result.getData());
	}

	@Override
	public void onLoaderReset(Loader<LoadResult<CommentsContainer>> loader) {
	}

}
