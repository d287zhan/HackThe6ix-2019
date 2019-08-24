package com.kevin.app.abstract_objects;

import com.kevin.app.ids.ObjectIds;
import com.kevin.app.ids.BlockId;

public abstract class BlockObject extends Object {

    protected BlockId bid;

    public BlockObject(float x, float y, ObjectIds ObjectId, BlockId blockId) {
        super(x, y, ObjectId);
        this.bid = blockId;
    }

    public BlockId getBlockId() {
        return this.bid;
    }
}