package com.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TypefacedTextView extends android.support.v7.widget.AppCompatTextView {

    public TypefacedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //Typeface.createFromAsset doesn't work in the layout editor. Skipping...
        if (isInEditMode()) {
            return;
        }

    //    TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TypefacedTextView);
    //    String fontName = styledAttrs.getString(R.styleable.TypefacedTextView_typeface);
     //  String fontName = "Roboto-Regular.ttf";
         String fontName="Roboto-Regular.ttf";
        // String fontName="HelveticaNeueLTStd-Bd.otf";
        
        //    styledAttrs.recycle();

        if (fontName != null) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            setTypeface(typeface);
        }
    }
}
