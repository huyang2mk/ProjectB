package com.lanou.yhz.article.grouparticle_b.bean.homebean.featuredbean;

import java.util.List;

/**
 * Created by zhaochunyu on 2017/3/2.
 */

public class VideoDetailsBean {


    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private SubjectDetailViewBean subjectDetailView;
        private VideoDetailViewBean videoDetailView;
        private AlbumDetailViewBean albumDetailView;
        private List<SilverRankListBean> silverRankList;
        private List<RecommendVideoListBean> recommendVideoList;

        public SubjectDetailViewBean getSubjectDetailView() {
            return subjectDetailView;
        }

        public void setSubjectDetailView(SubjectDetailViewBean subjectDetailView) {
            this.subjectDetailView = subjectDetailView;
        }

        public VideoDetailViewBean getVideoDetailView() {
            return videoDetailView;
        }

        public void setVideoDetailView(VideoDetailViewBean videoDetailView) {
            this.videoDetailView = videoDetailView;
        }

        public AlbumDetailViewBean getAlbumDetailView() {
            return albumDetailView;
        }

        public void setAlbumDetailView(AlbumDetailViewBean albumDetailView) {
            this.albumDetailView = albumDetailView;
        }

        public List<SilverRankListBean> getSilverRankList() {
            return silverRankList;
        }

        public void setSilverRankList(List<SilverRankListBean> silverRankList) {
            this.silverRankList = silverRankList;
        }

        public List<RecommendVideoListBean> getRecommendVideoList() {
            return recommendVideoList;
        }

        public void setRecommendVideoList(List<RecommendVideoListBean> recommendVideoList) {
            this.recommendVideoList = recommendVideoList;
        }

        public static class SubjectDetailViewBean {
            /**
             * videoCount : 0
             * videos : null
             * title : null
             * id : null
             */

            private int videoCount;
            private Object videos;
            private Object title;
            private Object id;

            public int getVideoCount() {
                return videoCount;
            }

            public void setVideoCount(int videoCount) {
                this.videoCount = videoCount;
            }

            public Object getVideos() {
                return videos;
            }

            public void setVideos(Object videos) {
                this.videos = videos;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }
        }

        public static class VideoDetailViewBean {


            private int viewCount;
            private int commentCount;
            private String playLink;
            private AuthorBean author;
            private String videoStatusType;
            private String brief;
            private String cover;
            private int rawDuration;
            private int watchLevel;
            private String title;
            private boolean isFavorite;
            private int rewardCount;
            private String duration;
            private String type;
            private int id;
            private List<TagListBean> tagList;
            private List<VideoFileViewBean> videoFileView;

            public int getViewCount() {
                return viewCount;
            }

            public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public String getPlayLink() {
                return playLink;
            }

            public void setPlayLink(String playLink) {
                this.playLink = playLink;
            }

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public String getVideoStatusType() {
                return videoStatusType;
            }

            public void setVideoStatusType(String videoStatusType) {
                this.videoStatusType = videoStatusType;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public int getRawDuration() {
                return rawDuration;
            }

            public void setRawDuration(int rawDuration) {
                this.rawDuration = rawDuration;
            }

            public int getWatchLevel() {
                return watchLevel;
            }

            public void setWatchLevel(int watchLevel) {
                this.watchLevel = watchLevel;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public boolean isIsFavorite() {
                return isFavorite;
            }

            public void setIsFavorite(boolean isFavorite) {
                this.isFavorite = isFavorite;
            }

            public int getRewardCount() {
                return rewardCount;
            }

            public void setRewardCount(int rewardCount) {
                this.rewardCount = rewardCount;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<TagListBean> getTagList() {
                return tagList;
            }

            public void setTagList(List<TagListBean> tagList) {
                this.tagList = tagList;
            }

            public List<VideoFileViewBean> getVideoFileView() {
                return videoFileView;
            }

            public void setVideoFileView(List<VideoFileViewBean> videoFileView) {
                this.videoFileView = videoFileView;
            }

            public static class AuthorBean {
                /**
                 * intro : 来微博找我耍啊，微博@波落菠萝
                 * score : 672
                 * headImgUrl : https://img.rr.tv/head/20170207/o_1486474670461.png
                 * fansCount : 871
                 * videoCount : 8
                 * isFocus : false
                 * roleInfo : normal
                 * nickName : 波落菠萝
                 * subjectCount : 0
                 * videoPlayCount : 323865
                 * level : 3
                 * sign : 来微博找我耍啊，微博@波落菠萝
                 * id : 11961255
                 */

                private String intro;
                private int score;
                private String headImgUrl;
                private int fansCount;
                private int videoCount;
                private boolean isFocus;
                private String roleInfo;
                private String nickName;
                private int subjectCount;
                private int videoPlayCount;
                private String level;
                private String sign;
                private int id;

                public String getIntro() {
                    return intro;
                }

                public void setIntro(String intro) {
                    this.intro = intro;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public String getHeadImgUrl() {
                    return headImgUrl;
                }

                public void setHeadImgUrl(String headImgUrl) {
                    this.headImgUrl = headImgUrl;
                }

                public int getFansCount() {
                    return fansCount;
                }

                public void setFansCount(int fansCount) {
                    this.fansCount = fansCount;
                }

                public int getVideoCount() {
                    return videoCount;
                }

                public void setVideoCount(int videoCount) {
                    this.videoCount = videoCount;
                }

                public boolean isIsFocus() {
                    return isFocus;
                }

                public void setIsFocus(boolean isFocus) {
                    this.isFocus = isFocus;
                }

                public String getRoleInfo() {
                    return roleInfo;
                }

                public void setRoleInfo(String roleInfo) {
                    this.roleInfo = roleInfo;
                }

                public String getNickName() {
                    return nickName;
                }

                public void setNickName(String nickName) {
                    this.nickName = nickName;
                }

                public int getSubjectCount() {
                    return subjectCount;
                }

                public void setSubjectCount(int subjectCount) {
                    this.subjectCount = subjectCount;
                }

                public int getVideoPlayCount() {
                    return videoPlayCount;
                }

                public void setVideoPlayCount(int videoPlayCount) {
                    this.videoPlayCount = videoPlayCount;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }

            public static class TagListBean {
                /**
                 * name : 扣熊深夜秀
                 * id : 444
                 */

                private String name;
                private int id;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }

            public static class VideoFileViewBean {
                /**
                 * playQuality : low
                 * fileSize : 16970312
                 */

                private String playQuality;
                private int fileSize;

                public String getPlayQuality() {
                    return playQuality;
                }

                public void setPlayQuality(String playQuality) {
                    this.playQuality = playQuality;
                }

                public int getFileSize() {
                    return fileSize;
                }

                public void setFileSize(int fileSize) {
                    this.fileSize = fileSize;
                }
            }
        }

        public static class AlbumDetailViewBean {
            /**
             * videoCount : 0
             * videos : null
             * title : null
             * id : null
             */

            private int videoCount;
            private Object videos;
            private Object title;
            private Object id;

            public int getVideoCount() {
                return videoCount;
            }

            public void setVideoCount(int videoCount) {
                this.videoCount = videoCount;
            }

            public Object getVideos() {
                return videos;
            }

            public void setVideos(Object videos) {
                this.videos = videos;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }
        }

        public static class SilverRankListBean {
            /**
             * userId : 5504538
             * headImgUrl : https://img.rr.tv/head/20160320/o_1458449431575.png
             * silver : 1
             * nickName : Huiggee5
             */

            private int userId;
            private String headImgUrl;
            private int silver;
            private String nickName;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getHeadImgUrl() {
                return headImgUrl;
            }

            public void setHeadImgUrl(String headImgUrl) {
                this.headImgUrl = headImgUrl;
            }

            public int getSilver() {
                return silver;
            }

            public void setSilver(int silver) {
                this.silver = silver;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }
        }

        public static class RecommendVideoListBean {
            /**
             * approvedTime : null
             * viewCount : 25946
             * commentCount : 41
             * author : {"intro":"","score":2,"headImgUrl":"https://img.rr.tv/head/20170209/o_1486624565605.jpg","roleInfo":"normal","nickName":"空耳同传君","level":"1","sign":"","id":10519301}
             * danmuCount : 0
             * brief : 【大佬重出江湖！前首相布莱尔：我要回来斗梅姨！】布莱尔再度“出山” 发表重要演讲~ 不仅狠批梅姨政府——任由脱欧公投的结果摆布，带着英国冲向悬崖... 还呼吁民众奋起反抗：当时投票你们不知道后果啊，现在终于看清了吧？重新再做个选择呗！！
             * cover : https://img.rr.tv/video/20170220/b_9605574819.png
             * title : 大佬重出江湖！前首相布莱尔：我要回来斗梅姨！
             * videoFileView : null
             * duration : 03:22
             * id : 106194
             */

            private Object approvedTime;
            private int viewCount;
            private int commentCount;
            private AuthorBeanX author;
            private int danmuCount;
            private String brief;
            private String cover;
            private String title;
            private Object videoFileView;
            private String duration;
            private int id;

            public Object getApprovedTime() {
                return approvedTime;
            }

            public void setApprovedTime(Object approvedTime) {
                this.approvedTime = approvedTime;
            }

            public int getViewCount() {
                return viewCount;
            }

            public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public AuthorBeanX getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBeanX author) {
                this.author = author;
            }

            public int getDanmuCount() {
                return danmuCount;
            }

            public void setDanmuCount(int danmuCount) {
                this.danmuCount = danmuCount;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getVideoFileView() {
                return videoFileView;
            }

            public void setVideoFileView(Object videoFileView) {
                this.videoFileView = videoFileView;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public static class AuthorBeanX {
                /**
                 * intro :
                 * score : 2
                 * headImgUrl : https://img.rr.tv/head/20170209/o_1486624565605.jpg
                 * roleInfo : normal
                 * nickName : 空耳同传君
                 * level : 1
                 * sign :
                 * id : 10519301
                 */

                private String intro;
                private int score;
                private String headImgUrl;
                private String roleInfo;
                private String nickName;
                private String level;
                private String sign;
                private int id;

                public String getIntro() {
                    return intro;
                }

                public void setIntro(String intro) {
                    this.intro = intro;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public String getHeadImgUrl() {
                    return headImgUrl;
                }

                public void setHeadImgUrl(String headImgUrl) {
                    this.headImgUrl = headImgUrl;
                }

                public String getRoleInfo() {
                    return roleInfo;
                }

                public void setRoleInfo(String roleInfo) {
                    this.roleInfo = roleInfo;
                }

                public String getNickName() {
                    return nickName;
                }

                public void setNickName(String nickName) {
                    this.nickName = nickName;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
