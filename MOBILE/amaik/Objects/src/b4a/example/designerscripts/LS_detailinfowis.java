package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_detailinfowis{

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
views.get("label1").vw.setTop((int)((10d / 100 * height)));
views.get("label1").vw.setHeight((int)((22d / 100 * height) - ((10d / 100 * height))));
views.get("label1").vw.setLeft((int)((0d / 100 * width)));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("imageview1").vw.setLeft((int)((6d / 100 * width)));
views.get("imageview1").vw.setWidth((int)((14d / 100 * width) - ((6d / 100 * width))));
views.get("imageview1").vw.setTop((int)((28d / 100 * height)));
views.get("imageview1").vw.setHeight((int)((34d / 100 * height) - ((28d / 100 * height))));
views.get("imageview2").vw.setLeft((int)((6d / 100 * width)));
views.get("imageview2").vw.setWidth((int)((14d / 100 * width) - ((6d / 100 * width))));
views.get("imageview2").vw.setTop((int)((40d / 100 * height)));
views.get("imageview2").vw.setHeight((int)((46d / 100 * height) - ((40d / 100 * height))));
views.get("imageview3").vw.setLeft((int)((6d / 100 * width)));
views.get("imageview3").vw.setWidth((int)((14d / 100 * width) - ((6d / 100 * width))));
views.get("imageview3").vw.setTop((int)((65d / 100 * height)));
views.get("imageview3").vw.setHeight((int)((72d / 100 * height) - ((65d / 100 * height))));
views.get("imageview4").vw.setLeft((int)((6d / 100 * width)));
views.get("imageview4").vw.setWidth((int)((14d / 100 * width) - ((6d / 100 * width))));
views.get("imageview4").vw.setTop((int)((52d / 100 * height)));
views.get("imageview4").vw.setHeight((int)((60d / 100 * height) - ((52d / 100 * height))));
views.get("label2").vw.setLeft((int)((18d / 100 * width)));
views.get("label2").vw.setWidth((int)((95d / 100 * width) - ((18d / 100 * width))));
views.get("label2").vw.setTop((int)((28d / 100 * height)));
views.get("label2").vw.setHeight((int)((38d / 100 * height) - ((28d / 100 * height))));
views.get("label3").vw.setLeft((int)((18d / 100 * width)));
views.get("label3").vw.setWidth((int)((95d / 100 * width) - ((18d / 100 * width))));
views.get("label3").vw.setTop((int)((40d / 100 * height)));
views.get("label3").vw.setHeight((int)((48d / 100 * height) - ((40d / 100 * height))));
views.get("label4").vw.setLeft((int)((18d / 100 * width)));
views.get("label4").vw.setWidth((int)((95d / 100 * width) - ((18d / 100 * width))));
views.get("label4").vw.setTop((int)((65d / 100 * height)));
views.get("label4").vw.setHeight((int)((72d / 100 * height) - ((65d / 100 * height))));
views.get("label5").vw.setLeft((int)((18d / 100 * width)));
views.get("label5").vw.setWidth((int)((40d / 100 * width) - ((18d / 100 * width))));
views.get("label5").vw.setTop((int)((52d / 100 * height)));
views.get("label5").vw.setHeight((int)((60d / 100 * height) - ((52d / 100 * height))));
views.get("label6").vw.setLeft((int)((45d / 100 * width)));
views.get("label6").vw.setWidth((int)((60d / 100 * width) - ((45d / 100 * width))));
views.get("label6").vw.setTop((int)((54d / 100 * height)));
views.get("label6").vw.setHeight((int)((60d / 100 * height) - ((54d / 100 * height))));
views.get("label7").vw.setLeft((int)((63d / 100 * width)));
views.get("label7").vw.setWidth((int)((85d / 100 * width) - ((63d / 100 * width))));
views.get("label7").vw.setTop((int)((52d / 100 * height)));
views.get("label7").vw.setHeight((int)((60d / 100 * height) - ((52d / 100 * height))));
views.get("button1").vw.setLeft((int)((13d / 100 * width)));
views.get("button1").vw.setWidth((int)((47d / 100 * width) - ((13d / 100 * width))));
views.get("button1").vw.setTop((int)((78d / 100 * height)));
views.get("button1").vw.setHeight((int)((88d / 100 * height) - ((78d / 100 * height))));
views.get("button2").vw.setLeft((int)((50d / 100 * width)));
views.get("button2").vw.setWidth((int)((86d / 100 * width) - ((50d / 100 * width))));
views.get("button2").vw.setTop((int)((78d / 100 * height)));
views.get("button2").vw.setHeight((int)((88d / 100 * height) - ((78d / 100 * height))));

}
}