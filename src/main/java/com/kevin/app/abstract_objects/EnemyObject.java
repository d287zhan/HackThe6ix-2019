package com.kevin.app.abstract_objects;

import com.kevin.app.ids.ObjectIds;
import com.kevin.app.ids.EnemyId;

public abstract class EnemyObject extends Object {

    protected EnemyId eid;

    public EnemyObject(float x, float y, ObjectIds ObjectId, EnemyId enemyId) {
        super(x, y, ObjectId);
        this.eid = enemyId;
    }

}