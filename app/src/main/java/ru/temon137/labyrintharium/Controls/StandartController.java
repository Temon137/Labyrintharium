package ru.temon137.labyrintharium.Controls;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import ru.temon137.labyrintharium.Render.IRenderable;
import ru.temon137.labyrintharium.World.GameObjects.Beings.Being;
import ru.temon137.labyrintharium.World.World;


public class StandartController implements IController, IRenderable {
    private SurfaceView surfaceView;
    private Paint paint;

    private int shifterWidth;
    private int shifterHeight;
    private float coeff;
    //=============


    public StandartController(@NonNull SurfaceView surfaceView) {
        this.surfaceView = surfaceView;

        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(1);
        paint.setFilterBitmap(false);
        paint.setAntiAlias(false);

        shifterWidth = surfaceView.getWidth() / 2;
        shifterHeight = surfaceView.getHeight();
        coeff = (float) shifterWidth / (float) shifterHeight;
    }

    @Override
    public synchronized void handleEvent(MotionEvent event) {
        if (event.getX() <= shifterWidth && event.getY() <= shifterHeight)
            shift(event);
    }

    @Override
    public SurfaceHolder getSurfaceHolder() {
        return surfaceView.getHolder();
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawLine(0, 0, shifterWidth, shifterHeight, paint);
        canvas.drawLine(shifterWidth, 0, 0, shifterHeight, paint);
    }


    private void shift(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float y1 = y * coeff;
        float y2 = shifterWidth - y1;

        if (x <= y1 && x < y2)
            World.getGamer().move(Being.Cource.Left);
        if (x > y1 && x < y2)
            World.getGamer().move(Being.Cource.Up);
        if (x > y1 && x >= y2)
            World.getGamer().move(Being.Cource.Right);
        if (x <= y1 && x >= y2)
            World.getGamer().move(Being.Cource.Down);
    }
}
