package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_radius{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("button1").vw.setLeft((int)((62d / 100 * width)));
views.get("button1").vw.setWidth((int)((98d / 100 * width) - ((62d / 100 * width))));
views.get("button1").vw.setTop((int)((42d / 100 * height)));
views.get("button1").vw.setHeight((int)((50d / 100 * height) - ((42d / 100 * height))));
views.get("seekbar1").vw.setLeft((int)((2d / 100 * width)));
views.get("seekbar1").vw.setWidth((int)((60d / 100 * width) - ((2d / 100 * width))));
views.get("seekbar1").vw.setTop((int)((42d / 100 * height)));
views.get("seekbar1").vw.setHeight((int)((50d / 100 * height) - ((42d / 100 * height))));
views.get("listview1").vw.setLeft((int)((2d / 100 * width)));
views.get("listview1").vw.setWidth((int)((98d / 100 * width) - ((2d / 100 * width))));
views.get("listview1").vw.setTop((int)((6d / 100 * height)));
views.get("listview1").vw.setHeight((int)((40d / 100 * height) - ((6d / 100 * height))));
views.get("webview1").vw.setLeft((int)((2d / 100 * width)));
views.get("webview1").vw.setWidth((int)((98d / 100 * width) - ((2d / 100 * width))));
views.get("webview1").vw.setTop((int)((52d / 100 * height)));
views.get("webview1").vw.setHeight((int)((98d / 100 * height) - ((52d / 100 * height))));

}
}