package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_wis_nama{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("panel2").vw.setLeft((int)(0d));
views.get("panel2").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel2").vw.setTop((int)(0d));
views.get("panel2").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("label1").vw.setTop((int)((10d / 100 * height)));
views.get("label1").vw.setHeight((int)((22d / 100 * height) - ((10d / 100 * height))));
views.get("label1").vw.setLeft((int)((0d / 100 * width)));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("edittext1").vw.setTop((int)((25d / 100 * height)));
views.get("edittext1").vw.setHeight((int)((35d / 100 * height) - ((25d / 100 * height))));
views.get("edittext1").vw.setLeft((int)((0d / 100 * width)));
views.get("edittext1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("label2").vw.setTop((int)((10d / 100 * height)));
views.get("label2").vw.setHeight((int)((22d / 100 * height) - ((10d / 100 * height))));
views.get("label2").vw.setLeft((int)((0d / 100 * width)));
views.get("label2").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("listview1").vw.setTop((int)((37d / 100 * height)));
views.get("listview1").vw.setHeight((int)((60d / 100 * height) - ((37d / 100 * height))));
views.get("listview1").vw.setLeft((int)((0d / 100 * width)));
views.get("listview1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("webview1").vw.setTop((int)((62d / 100 * height)));
views.get("webview1").vw.setHeight((int)((98d / 100 * height) - ((62d / 100 * height))));
views.get("webview1").vw.setLeft((int)((0d / 100 * width)));
views.get("webview1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));

}
}