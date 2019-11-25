package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_detailinform{

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
views.get("label1").vw.setTop((int)((5d / 100 * height)));
views.get("label1").vw.setHeight((int)((18d / 100 * height) - ((5d / 100 * height))));
views.get("label1").vw.setLeft((int)((0d / 100 * width)));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - ((0d / 100 * width))));
views.get("imageview1").vw.setLeft((int)((6d / 100 * width)));
views.get("imageview1").vw.setWidth((int)((16d / 100 * width) - ((6d / 100 * width))));
views.get("imageview1").vw.setTop((int)((22d / 100 * height)));
views.get("imageview1").vw.setHeight((int)((32d / 100 * height) - ((22d / 100 * height))));
views.get("imageview2").vw.setLeft((int)((6d / 100 * width)));
views.get("imageview2").vw.setWidth((int)((16d / 100 * width) - ((6d / 100 * width))));
views.get("imageview2").vw.setTop((int)((34d / 100 * height)));
views.get("imageview2").vw.setHeight((int)((44d / 100 * height) - ((34d / 100 * height))));
views.get("imageview3").vw.setLeft((int)((6d / 100 * width)));
views.get("imageview3").vw.setWidth((int)((16d / 100 * width) - ((6d / 100 * width))));
views.get("imageview3").vw.setTop((int)((46d / 100 * height)));
views.get("imageview3").vw.setHeight((int)((58d / 100 * height) - ((46d / 100 * height))));
views.get("imageview4").vw.setLeft((int)((6d / 100 * width)));
views.get("imageview4").vw.setWidth((int)((16d / 100 * width) - ((6d / 100 * width))));
views.get("imageview4").vw.setTop((int)((60d / 100 * height)));
views.get("imageview4").vw.setHeight((int)((70d / 100 * height) - ((60d / 100 * height))));
views.get("imageview5").vw.setLeft((int)((6d / 100 * width)));
views.get("imageview5").vw.setWidth((int)((16d / 100 * width) - ((6d / 100 * width))));
views.get("imageview5").vw.setTop((int)((72d / 100 * height)));
views.get("imageview5").vw.setHeight((int)((82d / 100 * height) - ((72d / 100 * height))));
views.get("label2").vw.setLeft((int)((18d / 100 * width)));
views.get("label2").vw.setWidth((int)((95d / 100 * width) - ((18d / 100 * width))));
views.get("label2").vw.setTop((int)((22d / 100 * height)));
views.get("label2").vw.setHeight((int)((32d / 100 * height) - ((22d / 100 * height))));
views.get("label3").vw.setLeft((int)((18d / 100 * width)));
views.get("label3").vw.setWidth((int)((95d / 100 * width) - ((18d / 100 * width))));
views.get("label3").vw.setTop((int)((34d / 100 * height)));
views.get("label3").vw.setHeight((int)((44d / 100 * height) - ((34d / 100 * height))));
views.get("label4").vw.setLeft((int)((18d / 100 * width)));
views.get("label4").vw.setWidth((int)((95d / 100 * width) - ((18d / 100 * width))));
views.get("label4").vw.setTop((int)((46d / 100 * height)));
views.get("label4").vw.setHeight((int)((58d / 100 * height) - ((46d / 100 * height))));
views.get("label5").vw.setLeft((int)((18d / 100 * width)));
views.get("label5").vw.setWidth((int)((95d / 100 * width) - ((18d / 100 * width))));
views.get("label5").vw.setTop((int)((60d / 100 * height)));
views.get("label5").vw.setHeight((int)((70d / 100 * height) - ((60d / 100 * height))));
views.get("label6").vw.setLeft((int)((18d / 100 * width)));
views.get("label6").vw.setWidth((int)((38d / 100 * width) - ((18d / 100 * width))));
//BA.debugLineNum = 29;BA.debugLine="Label6.SetTopAndBottom(72%y,82%y)"[detailinform/General script]
views.get("label6").vw.setTop((int)((72d / 100 * height)));
views.get("label6").vw.setHeight((int)((82d / 100 * height) - ((72d / 100 * height))));
//BA.debugLineNum = 30;BA.debugLine="Label7.SetLeftAndRight(43%x,60%x)"[detailinform/General script]
views.get("label7").vw.setLeft((int)((43d / 100 * width)));
views.get("label7").vw.setWidth((int)((60d / 100 * width) - ((43d / 100 * width))));
//BA.debugLineNum = 31;BA.debugLine="Label7.SetTopAndBottom(74%y,84%y)"[detailinform/General script]
views.get("label7").vw.setTop((int)((74d / 100 * height)));
views.get("label7").vw.setHeight((int)((84d / 100 * height) - ((74d / 100 * height))));
//BA.debugLineNum = 32;BA.debugLine="Label8.SetLeftAndRight(65%x,85%x)"[detailinform/General script]
views.get("label8").vw.setLeft((int)((65d / 100 * width)));
views.get("label8").vw.setWidth((int)((85d / 100 * width) - ((65d / 100 * width))));
//BA.debugLineNum = 33;BA.debugLine="Label8.SetTopAndBottom(72%y,82%y)"[detailinform/General script]
views.get("label8").vw.setTop((int)((72d / 100 * height)));
views.get("label8").vw.setHeight((int)((82d / 100 * height) - ((72d / 100 * height))));
//BA.debugLineNum = 35;BA.debugLine="Button1.SetLeftAndRight(13%x,45%x)"[detailinform/General script]
views.get("button1").vw.setLeft((int)((13d / 100 * width)));
views.get("button1").vw.setWidth((int)((45d / 100 * width) - ((13d / 100 * width))));
//BA.debugLineNum = 36;BA.debugLine="Button1.SetTopAndBottom(87%y,97%y)"[detailinform/General script]
views.get("button1").vw.setTop((int)((87d / 100 * height)));
views.get("button1").vw.setHeight((int)((97d / 100 * height) - ((87d / 100 * height))));
//BA.debugLineNum = 37;BA.debugLine="Button2.SetLeftAndRight(60%x,90%x)"[detailinform/General script]
views.get("button2").vw.setLeft((int)((60d / 100 * width)));
views.get("button2").vw.setWidth((int)((90d / 100 * width) - ((60d / 100 * width))));
//BA.debugLineNum = 38;BA.debugLine="Button2.SetTopAndBottom(87%y,97%y)"[detailinform/General script]
views.get("button2").vw.setTop((int)((87d / 100 * height)));
views.get("button2").vw.setHeight((int)((97d / 100 * height) - ((87d / 100 * height))));

}
}