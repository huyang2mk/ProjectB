package com.lanou.yhz.article.grouparticle_b.bean.searchbean;

import java.util.List;

/**
 * Created by dllo on 17/3/2.
 */

public class SearchBean {

    /**
     * code : 0000
     * msg :
     * data : {"wordList":["行尸走肉","神盾局特工","闪电侠","破产姐妹","吸血鬼日记","生活大爆炸","生化危机","权力的游戏"]}
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
        private List<String> wordList;

        public List<String> getWordList() {
            return wordList;
        }

        public void setWordList(List<String> wordList) {
            this.wordList = wordList;
        }
    }
}
