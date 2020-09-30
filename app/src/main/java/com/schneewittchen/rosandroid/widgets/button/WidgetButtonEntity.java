package com.schneewittchen.rosandroid.widgets.button;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.model.entities.SubPubNoteEntity;
import com.schneewittchen.rosandroid.model.entities.WidgetEntity;
import com.schneewittchen.rosandroid.widgets.base.BaseDetailViewHolder;
import com.schneewittchen.rosandroid.widgets.base.BaseEntity;
import com.schneewittchen.rosandroid.widgets.base.BaseNode;
import com.schneewittchen.rosandroid.widgets.base.BaseView;


/**
 * TODO: Description        messageText.setText(entity.message_str);

 *
 * @author Jonathan Camargo
 * @version 1.1.1
 * @created on 31.01.20
 * @updated on 10.05.20
 * @modified by Jonathan Camargo
 */
public class WidgetButtonEntity extends BaseEntity {

    public WidgetButtonEntity() {
        this.setType("Button");
        this.width = 4;
        this.height = 4;
        this.subPubNoteEntity = new SubPubNoteEntity();
        this.subPubNoteEntity.topic = "String";
        this.subPubNoteEntity.messageType = std_msgs.String._TYPE;
        this.messageStr= "";

    }

    @Override
    public String getName() {
        return "Button";
    }

    @Override
    public Class<? extends BaseView> getViewType() {
        return ButtonView.class;
    }

    @Override
    public int getWidgetDetailViewId() {
        return R.layout.widget_detail_button;
    }

    @Override
    public Class<? extends BaseDetailViewHolder> getDetailViewHolderType() {
        return ButtonDetailVH.class;
    }

    @Override
    public Class<? extends BaseNode> getNodeType() {
        return ButtonNode.class;
    }



    @Override
    public void insert(WidgetEntity entity) {
        super.insert(entity);
        this.subPubNoteEntity.topic = entity.subPubNoteEntity.topic;
        this.subPubNoteEntity.messageType = entity.subPubNoteEntity.messageType;
        this.messageStr = entity.messageStr;
    }

    @Override
    public WidgetButtonEntity copy() {
        WidgetButtonEntity newEnt = new WidgetButtonEntity();
        newEnt.insert(this);
        return newEnt;
    }

    @Override
    public boolean equalContent(BaseEntity widget) {
        if (!(widget instanceof WidgetButtonEntity))
            return false;

        WidgetButtonEntity other = (WidgetButtonEntity) widget;

        return this.subPubNoteEntity.equals(other.subPubNoteEntity)
                && this.messageStr == other.messageStr;
    }

}
