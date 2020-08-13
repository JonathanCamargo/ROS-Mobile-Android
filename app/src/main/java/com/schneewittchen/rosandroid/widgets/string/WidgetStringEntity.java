package com.schneewittchen.rosandroid.widgets.string;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.model.entities.SubPubNoteEntity;
import com.schneewittchen.rosandroid.model.entities.WidgetEntity;
import com.schneewittchen.rosandroid.widgets.base.BaseDetailViewHolder;
import com.schneewittchen.rosandroid.widgets.base.BaseEntity;
import com.schneewittchen.rosandroid.widgets.base.BaseNode;
import com.schneewittchen.rosandroid.widgets.base.BaseView;

/**
 * TODO: Description
 *
 * @author
 * @version
 * @created
 * @updated
 * @modified
 */
public class WidgetStringEntity extends BaseEntity {

    String topic;
    int colorScheme;
    boolean drawBehind;
    boolean useTimeStamp;


    public WidgetStringEntity() {
        this.setType("String");

        this.width = 4;
        this.height = 3;
        this.subPubNoteEntity = new SubPubNoteEntity();
        this.subPubNoteEntity.topic = "text";
        this.subPubNoteEntity.messageType = std_msgs.String._TYPE;
    }


    @Override
    public String getName() {
        return "String";
    }

    @Override
    public Class<? extends BaseView> getViewType() {
        return StringView.class;
    }

    // TODO: Add own layout for image style?
    @Override
    public int getWidgetDetailViewId() {
        return R.layout.widget_detail_string;
    }

    @Override
    public Class<? extends BaseDetailViewHolder> getDetailViewHolderType() {
        return StringDetailVH.class;
    }

    @Override
    public Class<? extends BaseNode> getNodeType() {
        return StringNode.class;
    }


    @Override
    public void insert(WidgetEntity entity) {
        super.insert(entity);

        this.subPubNoteEntity.topic = entity.subPubNoteEntity.topic;
        this.subPubNoteEntity.messageType = entity.subPubNoteEntity.messageType;
    }

    @Override
    public WidgetStringEntity copy() {
        WidgetStringEntity newEnt = new WidgetStringEntity();
        newEnt.insert(this);

        return newEnt;
    }

    @Override
    public boolean equalContent(BaseEntity widget) {
        if (!(widget instanceof WidgetStringEntity))
            return false;

        WidgetStringEntity other = (WidgetStringEntity) widget;

        return this.subPubNoteEntity.equals(other.subPubNoteEntity);
    }
}

