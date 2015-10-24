package four.pda.ui.article.list;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import four.pda.R;
import four.pda.ui.CursorRecyclerViewAdapter;

/**
 * Created by asavinova on 10/04/15.
 */
public class ArticlesAdapter extends CursorRecyclerViewAdapter<ViewHolder> {

	private LayoutInflater inflater;

	public ArticlesAdapter(Context context, Cursor cursor) {
		super(context, cursor);
		inflater = LayoutInflater.from(context);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.article_list_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
		viewHolder.setCursor(cursor);
	}

}
