package com.lanou.yhz.article.grouparticle_b.bean.homebean.newestbean;

import java.util.List;

/**
 * Created by dllo on 17/2/21.
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖镇楼                  BUG辟易
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 */

public class NewTitleBean {

    /**
     * code : 0000
     * msg :
     * data : {"category":[{"parentId":2,"leafCategory":[],"name":"热点","id":129},{"parentId":7,"leafCategory":[],"name":"翻唱MV","id":132},{"parentId":10,"leafCategory":[],"name":"极限运动","id":140},{"parentId":9,"leafCategory":[],"name":"搞笑","id":142},{"parentId":9,"leafCategory":[],"name":"美食","id":144},{"parentId":9,"leafCategory":[],"name":"萌宠","id":145},{"parentId":9,"leafCategory":[],"name":"美女","id":146},{"parentId":3,"leafCategory":[],"name":"预告","id":147},{"parentId":3,"leafCategory":[],"name":"美漫","id":150},{"parentId":8,"leafCategory":[],"name":"科普","id":154},{"parentId":5,"leafCategory":[],"name":"外语教学","id":156}]}
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
             * parentId : 2
             * leafCategory : []
             * name : 热点
             * id : 129
             */

            private int parentId;
            private String name;
            private int id;
            private List<?> leafCategory;

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
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

            public List<?> getLeafCategory() {
                return leafCategory;
            }

            public void setLeafCategory(List<?> leafCategory) {
                this.leafCategory = leafCategory;
            }
        }
    }
}
