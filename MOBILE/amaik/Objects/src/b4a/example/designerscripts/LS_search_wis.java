package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_search_wis{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("imageview1").vw.setLeft((int)((33d / 100 * width)));
views.get("imageview1").vw.setWidth((int)((70d / 100 * width) - ((33d / 100 * width))));
views.get("imageview1").vw.setTop((int)((6d / 100 * height)));
views.get("imageview1").vw.setHeight((int)((30d / 100 * height) - ((6d / 100 * height))));
views.get("label1").vw.setLeft((int)((4d / 100 * width)));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - ((4d / 100 * width))));
views.get("label1").vw.setTop((int)((28d / 100 * height)));
views.get("label1").vw.setHeight((int)((38d / 100 * height) - ((28d / 100 * height))));
views.get("button1").vw.setLeft((int)((20d / 100 * width)));
views.get("button1").vw.setWidth((int)((80d / 100 * width) - ((20d / 100 * width))));
views.get("button1").vw.setTop((int)((40d / 100 * height)));
views.get("button1").vw.setHeight((int)((58d / 100 * height) - ((40d / 100 * height))));
views.get("listview1").vw.setLeft((int)((20d / 100 * width)));
views.get("listview1").vw.setWidth((int)((80d / 100 * width) - ((20d / 100 * width))));
views.get("listview1").vw.setTop((int)((56d / 100 * height)));
views.get("listview1").vw.setHeight((int)((88d / 100 * height) - ((56d / 100 * height))));

}
}