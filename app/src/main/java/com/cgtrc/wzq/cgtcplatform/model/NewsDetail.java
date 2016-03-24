package com.cgtrc.wzq.cgtcplatform.model;

import com.cgtrc.wzq.cgtcplatform.inerf.INewsDetail;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bym on 16/3/24.
 * TODO:这个业务数据结构为copy 后续再匹配
 */
public class NewsDetail extends RealmObject implements INewsDetail {

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    @PrimaryKey
    private int id;
    private RealmList<RealmString> css;

    public RealmList<RealmString> getCss() {
        return css;
    }

    public void setCss(RealmList<RealmString> css) {
        this.css = css;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getBody() {
        return body;
    }

    public String getImage_source() {
        return image_source;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getShare_url() {
        return share_url;
    }

    public int getId() {
        return id;
    }
}
