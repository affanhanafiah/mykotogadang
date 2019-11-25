package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_galeriik{

public static void LS_320x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("panel2").vw.setTop((int)(0d));
views.get("panel2").vw.setHeight((int)((10d / 100 * height) - (0d)));
views.get("panel2").vw.setLeft((int)(0d));
views.get("panel2").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("imageview2").vw.setLeft((int)((4d / 100 * width)));
views.get("imageview2").vw.setWidth((int)((13d / 100 * width) - ((4d / 100 * width))));
views.get("imageview2").vw.setTop((int)((2d / 100 * height)));
views.get("imageview2").vw.setHeight((int)((8d / 100 * height) - ((2d / 100 * height))));
views.get("label2").vw.setLeft((int)((16d / 100 * width)));
views.get("label2").vw.setWidth((int)((100d / 100 * width) - ((16d / 100 * width))));
views.get("label2").vw.setTop((int)(0d));
views.get("label2").vw.setHeight((int)((10d / 100 * height) - (0d)));
views.get("imageview1").vw.setLeft((int)((10d / 100 * width)));
views.get("imageview1").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
views.get("imageview1").vw.setTop((int)((23d / 100 * height)));
views.get("imageview1").vw.setHeight((int)((58d / 100 * height) - ((23d / 100 * height))));
views.get("imageview3").vw.setLeft((int)((10d / 100 * width)));
views.get("imageview3").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
views.get("imageview3").vw.setTop((int)((60d / 100 * height)));
views.get("imageview3").vw.setHeight((int)((95d / 100 * height) - ((60d / 100 * height))));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}