package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_posisiik{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("panel2").vw.setTop((int)(0d));
views.get("panel2").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("panel2").vw.setLeft((int)(0d));
views.get("panel2").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("label1").vw.setLeft((int)((0d / 100 * width)));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("label1").vw.setTop((int)((6d / 100 * height)));
views.get("label1").vw.setHeight((int)((17d / 100 * height) - ((6d / 100 * height))));
views.get("label2").vw.setLeft((int)((0d / 100 * width)));
views.get("label2").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("label2").vw.setTop((int)((3d / 100 * height)));
views.get("label2").vw.setHeight((int)((21d / 100 * height) - ((3d / 100 * height))));
views.get("webview1").vw.setTop((int)((19d / 100 * height)));
views.get("webview1").vw.setHeight((int)((55d / 100 * height) - ((19d / 100 * height))));
views.get("webview1").vw.setLeft((int)((0d / 100 * width)));
views.get("webview1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("webview2").vw.setTop((int)((2d / 100 * height)));
views.get("webview2").vw.setHeight((int)((58d / 100 * height) - ((2d / 100 * height))));
views.get("webview2").vw.setLeft((int)((0d / 100 * width)));
views.get("webview2").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("listview1").vw.setTop((int)((60d / 100 * height)));
views.get("listview1").vw.setHeight((int)((150d / 100 * width) - ((60d / 100 * height))));
views.get("listview1").vw.setLeft((int)((0d / 100 * height)));
views.get("listview1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * height))));
views.get("listview2").vw.setTop((int)((57d / 100 * height)));
views.get("listview2").vw.setHeight((int)((80d / 100 * height) - ((57d / 100 * height))));
views.get("listview2").vw.setLeft((int)((0d / 100 * width)));
views.get("listview2").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("button1").vw.setTop((int)((82d / 100 * height)));
views.get("button1").vw.setHeight((int)((94d / 100 * height) - ((82d / 100 * height))));
views.get("button1").vw.setLeft((int)((6d / 100 * width)));
views.get("button1").vw.setWidth((int)((34d / 100 * width) - ((6d / 100 * width))));
views.get("button2").vw.setTop((int)((82d / 100 * height)));
views.get("button2").vw.setHeight((int)((94d / 100 * height) - ((82d / 100 * height))));
views.get("button2").vw.setLeft((int)((67d / 100 * width)));
views.get("button2").vw.setWidth((int)((94d / 100 * width) - ((67d / 100 * width))));
views.get("button3").vw.setTop((int)((82d / 100 * height)));
views.get("button3").vw.setHeight((int)((94d / 100 * height) - ((82d / 100 * height))));
views.get("button3").vw.setLeft((int)((36d / 100 * width)));
views.get("button3").vw.setWidth((int)((65d / 100 * width) - ((36d / 100 * width))));

}
}