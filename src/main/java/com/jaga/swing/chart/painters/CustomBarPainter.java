package com.jaga.swing.chart.painters;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

import org.jfree.chart.renderer.category.BarPainter;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.ui.GradientPaintTransformer;
import com.jhw.swing.util.MaterialDrawingUtils;
import com.jhw.swing.util.enums.GradientEnum;
import com.jhw.swing.material.standards.MaterialColors;
import org.jfree.ui.RectangleEdge;

/**
 * Sacado de internet, el autor exacto se desconoce, pero si fue retocado y por
 * eso se incluyen variso autores.
 *
 * @author Unkwnown
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class CustomBarPainter implements BarPainter, Serializable {

    protected GradientEnum gradient = GradientEnum.HORIZONTAL;
    private static final long serialVersionUID = 1L;

    public CustomBarPainter() {
    }

    @Override
    public void paintBar(Graphics2D g2, BarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base) {
        g2 = MaterialDrawingUtils.getAliasedGraphics(g2);

        int x = (int) bar.getX();
        int y = (int) bar.getY();
        int w = (int) bar.getWidth();
        int h = (int) bar.getHeight();
        int arc = 10;

        Paint itemPaint = renderer.getItemPaint(row, column);
        Color c = (Color) itemPaint;
        if (itemPaint instanceof Color) {
            itemPaint = new GradientPaint(x + w / 2, y + h / 2, c, x + w / 2, (float) y + 2 * h, MaterialColors.BLACK);
        }
        GradientPaintTransformer t = renderer.getGradientPaintTransformer();
        if (t != null && itemPaint instanceof GradientPaint) {
            itemPaint = t.transform((GradientPaint) itemPaint, bar);
        }

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setPaint(itemPaint);
        g2.fillRoundRect(x, y, w, h, arc, arc);
        //g2.setPaint(itemPaint);
        //g2.fillRect(x, y + h / 3 - 2 * arc, w, h);

        // draw the outline...
        if (renderer.isDrawBarOutline()) {
            // &amp;&amp; state.getBarWidth() &gt; BAR_OUTLINE_WIDTH_THRESHOLD) {
            Stroke stroke = renderer.getItemOutlineStroke(row, column);
            Paint paint = renderer.getItemOutlinePaint(row, column);
            if (stroke != null && paint != null) {
                g2.setStroke(stroke);
                g2.setPaint(paint);
//                g2.draw(bar);
                g2.drawRoundRect(x, y, w, h, arc, arc);
            }
        }

//        g2.dispose();
    }

    public GradientEnum getGradient() {
        return gradient;
    }

    public void setGradient(GradientEnum gradient) {
        this.gradient = gradient;
    }

    public Paint getGradientePaint(Color c, int width, int height) {
        switch (this.gradient) {
            case HORIZONTAL:
                //return new GradientPaint(width / 2, 0, c, width/2, 10, MaterialColors.BLACK);
                return new GradientPaint((float) width / 2, 0f, c, (float) width / 2, (float) height, MaterialColors.BLACK);
            //return new GradientPaint((float) (getWidth() / 2), (float) 0, this.primaryColor, (float) (getWidth() / 2), (float) getHeight(), this.secundaryColor);
            default:
                return c;
        }
    }

    /**
     * Paints a single bar instance.
     *
     * @param g2 the graphics target.
     * @param renderer the renderer.
     * @param row the row index.
     * @param column the column index.
     * @param bar the bar
     * @param base indicates which side of the rectangle is the base of the bar.
     * @param pegShadow peg the shadow to the base of the bar?
     */
    @Override
    public void paintBarShadow(Graphics2D g2, BarRenderer renderer, int row,
            int column, RectangularShape bar, RectangleEdge base,
            boolean pegShadow) {

        // handle a special case - if the bar colour has alpha == 0, it is
        // invisible so we shouldn't draw any shadow
        Paint itemPaint = renderer.getItemPaint(row, column);
        if (itemPaint instanceof Color) {
            Color c = (Color) itemPaint;
            if (c.getAlpha() == 0) {
                return;
            }
        }

        RectangularShape shadow = createShadow(bar, renderer.getShadowXOffset(),
                renderer.getShadowYOffset(), base, pegShadow);
        g2.setPaint(renderer.getShadowPaint());
        g2.fill(shadow);

    }

    /**
     * Creates a shadow for the bar.
     *
     * @param bar the bar shape.
     * @param xOffset the x-offset for the shadow.
     * @param yOffset the y-offset for the shadow.
     * @param base the edge that is the base of the bar.
     * @param pegShadow peg the shadow to the base?
     *
     * @return A rectangle for the shadow.
     */
    private Rectangle2D createShadow(RectangularShape bar, double xOffset,
            double yOffset, RectangleEdge base, boolean pegShadow) {
        double x0 = bar.getMinX();
        double x1 = bar.getMaxX();
        double y0 = bar.getMinY();
        double y1 = bar.getMaxY();
        if (base == RectangleEdge.TOP) {
            x0 += xOffset;
            x1 += xOffset;
            if (!pegShadow) {
                y0 += yOffset;
            }
            y1 += yOffset;
        } else if (base == RectangleEdge.BOTTOM) {
            x0 += xOffset;
            x1 += xOffset;
            y0 += yOffset;
            if (!pegShadow) {
                y1 += yOffset;
            }
        } else if (base == RectangleEdge.LEFT) {
            if (!pegShadow) {
                x0 += xOffset;
            }
            x1 += xOffset;
            y0 += yOffset;
            y1 += yOffset;
        } else if (base == RectangleEdge.RIGHT) {
            x0 += xOffset;
            if (!pegShadow) {
                x1 += xOffset;
            }
            y0 += yOffset;
            y1 += yOffset;
        }

        return new Rectangle2D.Double(x0, y0, (x1 - x0), (y1 - y0));
    }

    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj the obj (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj instanceof StandardBarPainter;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

}
