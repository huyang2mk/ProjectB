package com.lanou.yhz.article.grouparticle_b.video;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yhz.article.grouparticle_b.R;
import com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean.VideoDetailsBean;
import com.lanou.yhz.article.grouparticle_b.ok.GlideManger;
import com.lanou.yhz.article.grouparticle_b.utils.SpacesItemDecoration;
import com.lanou.yhz.article.grouparticle_b.video.videorecommend.VideoRecommendAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by zhaochunyu on 2017/3/2.
 * @author zhaochunyu
 * 播放视频界面适配器
 */

public class VideoPlayerAdapter extends RecyclerView.Adapter {
    private Context context;
    private VideoDetailsBean data;

    public VideoPlayerAdapter(Context context) {
        this.context = context;
    }


    public void setData(VideoDetailsBean  data) {
        Log.d("VideoPlayerAdapter", "data:" + data);
        this.data = data;
        notifyDataSetChanged();
    }

    private static final int TITLE = 1;
    private static final int USER = 2;
    private static final int RECOMMENT = 3;
    private static final int RECOMMENT_HEADER = 4;


    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TITLE;
            case 1:
                return USER;
            case 2:
                return RECOMMENT_HEADER;
            case 3:
                return RECOMMENT;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TITLE:
                View viewTitle = LayoutInflater.from(context).inflate(R.layout.item_video_details_header, parent, false);
                holder = new VideoHolderTitle(viewTitle);
                break;
            case USER:
                View viewUser = LayoutInflater.from(context).inflate(R.layout.item_video_details_user, parent, false);
                holder = new VideoHolderUser(viewUser);
                break;
            case RECOMMENT:
                View viewRecomment = LayoutInflater.from(context).inflate(R.layout.item_video_details_recommend,parent,false);
                holder = new VideoHolderRecomment(viewRecomment);
                break;
            case RECOMMENT_HEADER:
                View viewRecommentHeader = LayoutInflater.from(context).inflate(R.layout.item_video_details_recommend_header,parent,false);
                holder = new VideoHolderRecomment(viewRecommentHeader);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TITLE:
                VideoHolderTitle holderTitle = (VideoHolderTitle) holder;
                holderTitle.titleTv.setText(data.getData().getVideoDetailView().getTitle());
                holderTitle.contentTv.setText(data.getData().getVideoDetailView().getBrief());
                holderTitle.numberTv.setText(data.getData().getVideoDetailView().getViewCount() + "次播放");

                break;
            case USER:
                VideoHolderUser holderUser = (VideoHolderUser) holder;
                GlideManger.getsInstance().loadImageView(context, data.getData().getVideoDetailView().getAuthor().getHeadImgUrl(), holderUser.roundIv);
                holderUser.nameTv.setText(data.getData().getVideoDetailView().getAuthor().getNickName());
                holderUser.numberTv.setText(data.getData().getVideoDetailView().getAuthor().getFansCount() + "人订阅");
                break;
            case RECOMMENT:
                VideoHolderRecomment holderRecomment = (VideoHolderRecomment) holder;
                VideoRecommendAdapter adapter = new VideoRecommendAdapter(context);
                adapter.setData(data.getData().getRecommendVideoList());
                int itemSpacing = 8;
                holderRecomment.recyclerView.addItemDecoration(new SpacesItemDecoration(itemSpacing));
                holderRecomment.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                holderRecomment.recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : 4;
    }

    class VideoHolderTitle extends RecyclerView.ViewHolder {
        TextView titleTv, numberTv, contentTv;
        public VideoHolderTitle(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.video_details_header_title);
            numberTv = (TextView) itemView.findViewById(R.id.video_details_header_number);
            contentTv = (TextView) itemView.findViewById(R.id.video_details_header_content);
        }
    }

    class VideoHolderUser extends RecyclerView.ViewHolder {
        RoundedImageView roundIv;
        TextView nameTv, numberTv;
        ImageView imageView;

        public VideoHolderUser(View itemView) {
            super(itemView);
            roundIv = (RoundedImageView) itemView.findViewById(R.id.video_details_user_roundimageview);
            nameTv = (TextView) itemView.findViewById(R.id.video_details_user_name);
            numberTv = (TextView) itemView.findViewById(R.id.video_details_user_number);
        }
    }
    class VideoHolderRecomment extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        public VideoHolderRecomment(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.item_video_details_recommend_recyclerview);
        }
    }
    class VideoHolderRecommentHeader extends RecyclerView.ViewHolder {
        public VideoHolderRecommentHeader(View itemView) {
            super(itemView);
        }
    }
}
