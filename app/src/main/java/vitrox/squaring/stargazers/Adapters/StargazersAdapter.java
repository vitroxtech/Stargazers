
package vitrox.squaring.stargazers.Adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;
import vitrox.squaring.stargazers.Model.GitResponse;
import vitrox.squaring.stargazers.R;


public class StargazersAdapter extends RecyclerView.Adapter<StargazersAdapter.ViewHolder> {

    private List<GitResponse> mDataSet;
    private Context mContext;

    public StargazersAdapter(Context context) {
        mDataSet = new ArrayList<>();
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        public TextView mTitleView;
        public ImageView mThumbImageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) mView.findViewById(R.id.itemTitle);
            mThumbImageView=(ImageView) mView.findViewById(R.id.rowThumbnail);
        }

        public void bind(final GitResponse gitResponse) {
            mTitleView.setText(gitResponse.getLogin());
            Glide.with(mContext).load(gitResponse.getAvatarUrl())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
                    .centerCrop().into(mThumbImageView);
        }
    }

    public void addData(List<GitResponse> data) {
        mDataSet=data;
        notifyDataSetChanged();
    }

    public void resetData() {
        mDataSet.clear();
        notifyDataSetChanged();
    }

    public List<GitResponse> getAllDataList()
    {
        return mDataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.bind(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        if (mDataSet == null) {
            return 0;
        }
        return mDataSet.size();
    }
}