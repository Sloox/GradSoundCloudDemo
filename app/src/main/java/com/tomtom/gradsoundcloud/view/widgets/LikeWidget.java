package com.tomtom.gradsoundcloud.view.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tomtom.gradsoundcloud.R;

/**
 * LikeWidget
 * A custom widget that is a feeble attempt of replicating
 * a "like section" seen on souncloud profile
 */
public class LikeWidget extends LinearLayout {
    private ImageView lImage;
    private TextView lTextView;

    /**
     * Instantiates a new Like widget.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public LikeWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.likewidget, 0, 0);
        String text = a.getString(R.styleable.likewidget_text);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_widget_like, this, true);
        lTextView = (TextView) view.findViewById(R.id.like_text);
        lImage = (ImageView) view.findViewById(R.id.like_icon);
        lTextView.setText(text);
    }

    /**
     * Set image icon.
     *
     * @param id the id
     */
    public void setImageIcon(int id) {
        lImage.setImageResource(id);
        lImage.refreshDrawableState();
    }

    /**
     * Set text.
     *
     * @param id the id
     */
    public void setText(String id) {
        lTextView.setText(id);
    }


}
