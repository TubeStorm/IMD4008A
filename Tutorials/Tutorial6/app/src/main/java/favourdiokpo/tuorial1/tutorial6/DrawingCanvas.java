package favourdiokpo.tuorial1.tutorial6;

import android.content.Context;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;



public class DrawingCanvas extends View {
    private Paint mPaint;
    private Path mPath;



    public DrawingCanvas(Context context) {
        super(context);
    }
}
