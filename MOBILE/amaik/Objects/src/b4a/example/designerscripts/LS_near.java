package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_near{

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
views.get("checkbox1").vw.setLeft((int)((6d / 100 * width)));
views.get("checkbox1").vw.setWidth((int)((33d / 100 * width) - ((6d / 100 * width))));
views.get("checkbox1").vw.setTop((int)((3d / 100 * height)));
views.get("checkbox1").vw.setHeight((int)((12d / 100 * height) - ((3d / 100 * height))));
views.get("checkbox2").vw.setLeft((int)((35d / 100 * width)));
views.get("checkbox2").vw.setWidth((int)((65d / 100 * width) - ((35d / 100 * width))));
views.get("checkbox2").vw.setTop((int)((3d / 100 * height)));
views.get("checkbox2").vw.setHeight((int)((12d / 100 * height) - ((3d / 100 * height))));
views.get("checkbox3").vw.setLeft((int)((67d / 100 * width)));
views.get("checkbox3").vw.setWidth((int)((94d / 100 * width) - ((67d / 100 * width))));
views.get("checkbox3").vw.setTop((int)((3d / 100 * height)));
views.get("checkbox3").vw.setHeight((int)((12d / 100 * height) - ((3d / 100 * height))));
views.get("checkbox4").vw.setLeft((int)((6d / 100 * width)));
views.get("checkbox4").vw.setWidth((int)((36d / 100 * width) - ((6d / 100 * width))));
views.get("checkbox4").vw.setTop((int)((15d / 100 * height)));
views.get("checkbox4").vw.setHeight((int)((24d / 100 * height) - ((15d / 100 * height))));
views.get("checkbox5").vw.setLeft((int)((38d / 100 * width)));
views.get("checkbox5").vw.setWidth((int)((66d / 100 * width) - ((38d / 100 * width))));
views.get("checkbox5").vw.setTop((int)((15d / 100 * height)));
views.get("checkbox5").vw.setHeight((int)((24d / 100 * height) - ((15d / 100 * height))));
views.get("listview6").vw.setLeft((int)((6d / 100 * width)));
views.get("listview6").vw.setWidth((int)((94d / 100 * width) - ((6d / 100 * width))));
views.get("listview6").vw.setTop((int)((37d / 100 * height)));
views.get("listview6").vw.setHeight((int)((58d / 100 * height) - ((37d / 100 * height))));
views.get("button1").vw.setLeft((int)((6d / 100 * width)));
views.get("button1").vw.setWidth((int)((94d / 100 * width) - ((6d / 100 * width))));
views.get("button1").vw.setTop((int)((26d / 100 * height)));
views.get("button1").vw.setHeight((int)((35d / 100 * height) - ((26d / 100 * height))));
views.get("webview2").vw.setLeft((int)((0d / 100 * width)));
views.get("webview2").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("webview2").vw.setTop((int)((60d / 100 * height)));
views.get("webview2").vw.setHeight((int)((100d / 100 * height) - ((60d / 100 * height))));

}
}