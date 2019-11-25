package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_search_umkm{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("label1").vw.setLeft((int)((0d / 100 * width)));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("label1").vw.setTop((int)((4d / 100 * height)));
views.get("label1").vw.setHeight((int)((15d / 100 * height) - ((4d / 100 * height))));
views.get("button1").vw.setLeft((int)((30d / 100 * width)));
views.get("button1").vw.setWidth((int)((70d / 100 * width) - ((30d / 100 * width))));
views.get("button1").vw.setTop((int)((17d / 100 * height)));
views.get("button1").vw.setHeight((int)((30d / 100 * height) - ((17d / 100 * height))));
views.get("listview1").vw.setLeft((int)((0d / 100 * width)));
views.get("listview1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("listview1").vw.setTop((int)((33d / 100 * height)));
views.get("listview1").vw.setHeight((int)((43d / 100 * height) - ((33d / 100 * height))));

}
}