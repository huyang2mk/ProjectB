package com.lanou.yhz.article.grouparticle_b.bean.takebean;

import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class TakeBean {
//    http://api.rr.tv/v3plus/category/all
//    token 39740d660ce56921bed639fda374e1f3c9032717
//            clientVersion 3.5.2
    /**
     * code : 0000
     * msg :
     * data : {"category":[{"parentId":null,"leafCategory":[{"fixed":false,"name":"广告短片","id":128},{"fixed":false,"name":"脱口秀","id":130},{"fixed":false,"name":"游戏","id":131},{"fixed":false,"name":"热点","id":129}],"name":"娱乐","id":2},{"parentId":null,"leafCategory":[{"fixed":false,"name":"美漫","id":150},{"fixed":false,"name":"原创/混剪","id":148},{"fixed":false,"name":"预告","id":147},{"fixed":false,"name":"访谈","id":149}],"name":"影视花絮","id":3},{"parentId":null,"leafCategory":[{"fixed":false,"name":"搞笑","id":142},{"fixed":false,"name":"美食","id":144},{"fixed":false,"name":"美女","id":146},{"fixed":false,"name":"萌宠","id":145},{"fixed":false,"name":"日常","id":143}],"name":"生活","id":9},{"parentId":null,"leafCategory":[{"fixed":false,"name":"科普","id":154},{"fixed":false,"name":"新科技","id":151},{"fixed":false,"name":"数码","id":152},{"fixed":false,"name":"机车机械","id":153}],"name":"科技","id":8},{"parentId":null,"leafCategory":[{"fixed":false,"name":"翻唱MV","id":132},{"fixed":false,"name":"现场","id":136},{"fixed":false,"name":"演奏","id":135},{"fixed":false,"name":"榜单","id":133}],"name":"音乐","id":7},{"parentId":null,"leafCategory":[{"fixed":false,"name":"极限运动","id":140},{"fixed":false,"name":"健身","id":141},{"fixed":false,"name":"NBA","id":137},{"fixed":false,"name":"搏击","id":139},{"fixed":false,"name":"足球","id":138}],"name":"体育","id":10},{"parentId":null,"leafCategory":[{"fixed":false,"name":"公开课","id":155},{"fixed":false,"name":"外语教学","id":156}],"name":"教育","id":5},{"parentId":null,"leafCategory":[{"fixed":false,"name":"科教","id":159},{"fixed":false,"name":"文史","id":158},{"fixed":false,"name":"自然","id":157}],"name":"纪录片","id":11}]}
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
        private List<CategoryBean> category;

        public List<CategoryBean> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryBean> category) {
            this.category = category;
        }

        public static class CategoryBean {
            /**
             * parentId : null
             * leafCategory : [{"fixed":false,"name":"广告短片","id":128},{"fixed":false,"name":"脱口秀","id":130},{"fixed":false,"name":"游戏","id":131},{"fixed":false,"name":"热点","id":129}]
             * name : 娱乐
             * id : 2
             */

            private Object parentId;
            private String name;
            private int id;
            private List<LeafCategoryBean> leafCategory;

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

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

            public List<LeafCategoryBean> getLeafCategory() {
                return leafCategory;
            }

            public void setLeafCategory(List<LeafCategoryBean> leafCategory) {
                this.leafCategory = leafCategory;
            }

            public static class LeafCategoryBean {
                /**
                 * fixed : false
                 * name : 广告短片
                 * id : 128
                 */

                private boolean fixed;
                private String name;
                private int id;

                public boolean isFixed() {
                    return fixed;
                }

                public void setFixed(boolean fixed) {
                    this.fixed = fixed;
                }

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
        }
    }
}
