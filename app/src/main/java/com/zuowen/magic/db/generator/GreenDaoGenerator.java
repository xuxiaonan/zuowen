package com.zuowen.magic.db.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by worm on 2016/1/17.
 */
public class GreenDaoGenerator {

    public static void main(String[] args) throws Exception {
//代表创建的数据库的版本号以及默认的java package，如果不修改默认的包名，生成的dao和model都会在该包下
        Schema schema = new Schema(1, "com.zuowen.magic.db.model");
        schema.setDefaultJavaPackageDao("com.zuowen.magic.db.dao");
        //生成的model中，我们可能需要加入自己的一些信息，但是又不希望下次生成的时候消失
        schema.enableKeepSectionsByDefault();
        //代表实体类是否支持active，用过php中yii框架的都应该清楚，实体类可以直接进行crud操作。
        // 我们不开启就好了，如果开启了的话，实体类将之间支持update, refresh, deleted 等操作。
        //schema.enableActiveEntitiesByDefault();
        //ActiveRecord
        addEntity(schema);
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

    private static void addEntity(Schema schema) {
        Entity magicList = schema.addEntity("MagicList");
        magicList.addIdProperty().primaryKey();
        magicList.addStringProperty("pid");
        magicList.addStringProperty("aid");
        magicList.addStringProperty("title");
        magicList.addStringProperty("url");
        magicList.addStringProperty("intro");
        magicList.addStringProperty("cid");
        magicList.addStringProperty("author");
        magicList.addStringProperty("date");
        magicList.addStringProperty("classname");
        magicList.addStringProperty("tags");
        magicList.addBooleanProperty("collect");



    }


}
