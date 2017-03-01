package com.lanou.yhz.article.grouparticle_b.bean.homebean.newestbean;

import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */

public class VideoViewBean {

    /**
     * code : 0000
     * msg :
     * data : {"m3u8":{"url":"http://qcloud.rrmj.tv/2017/02/28/737526add2194f6587492c078147ef8f.mp4.f30.mp4?sign=8a4ea162c1c540b468217fb08d45c5f9&t=58b55035&clientType=null&clientVersion3.5.3","currentQuality":"high","qualityArr":["low","high","super"],"parserType":"DIRECT","source":""},"video":{"watchLevel":0}}
     */

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
        /**
         * m3u8 : {"url":"http://qcloud.rrmj.tv/2017/02/28/737526add2194f6587492c078147ef8f.mp4.f30.mp4?sign=8a4ea162c1c540b468217fb08d45c5f9&t=58b55035&clientType=null&clientVersion3.5.3","currentQuality":"high","qualityArr":["low","high","super"],"parserType":"DIRECT","source":""}
         * video : {"watchLevel":0}
         */

        private M3u8Bean m3u8;
        private VideoBean video;

        public M3u8Bean getM3u8() {
            return m3u8;
        }

        public void setM3u8(M3u8Bean m3u8) {
            this.m3u8 = m3u8;
        }

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public static class M3u8Bean {
            /**
             * url : http://qcloud.rrmj.tv/2017/02/28/737526add2194f6587492c078147ef8f.mp4.f30.mp4?sign=8a4ea162c1c540b468217fb08d45c5f9&t=58b55035&clientType=null&clientVersion3.5.3
             * currentQuality : high
             * qualityArr : ["low","high","super"]
             * parserType : DIRECT
             * source :
             */

            private String url;
            private String currentQuality;
            private String parserType;
            private String source;
            private List<String> qualityArr;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCurrentQuality() {
                return currentQuality;
            }

            public void setCurrentQuality(String currentQuality) {
                this.currentQuality = currentQuality;
            }

            public String getParserType() {
                return parserType;
            }

            public void setParserType(String parserType) {
                this.parserType = parserType;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public List<String> getQualityArr() {
                return qualityArr;
            }

            public void setQualityArr(List<String> qualityArr) {
                this.qualityArr = qualityArr;
            }
        }

        public static class VideoBean {
            /**
             * watchLevel : 0
             */

            private int watchLevel;

            public int getWatchLevel() {
                return watchLevel;
            }

            public void setWatchLevel(int watchLevel) {
                this.watchLevel = watchLevel;
            }
        }
    }
}
