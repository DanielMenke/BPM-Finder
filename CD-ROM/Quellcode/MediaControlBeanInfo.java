package bpm.finder;

import java.beans.*;

public class MediaControlBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( bpm.finder.MediaControl.class , null ); // NOI18N//GEN-HEADEREND:BeanDescriptor
    // Here you can add code for customizing the BeanDescriptor.

        return beanDescriptor;     }//GEN-LAST:BeanDescriptor


    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_background = 0;
    private static final int PROPERTY_baselineOffset = 1;
    private static final int PROPERTY_blendMode = 2;
    private static final int PROPERTY_border = 3;
    private static final int PROPERTY_bottom = 4;
    private static final int PROPERTY_boundsInLocal = 5;
    private static final int PROPERTY_boundsInParent = 6;
    private static final int PROPERTY_cache = 7;
    private static final int PROPERTY_cacheHint = 8;
    private static final int PROPERTY_cacheShape = 9;
    private static final int PROPERTY_center = 10;
    private static final int PROPERTY_centerShape = 11;
    private static final int PROPERTY_children = 12;
    private static final int PROPERTY_childrenUnmodifiable = 13;
    private static final int PROPERTY_clip = 14;
    private static final int PROPERTY_contentBias = 15;
    private static final int PROPERTY_cssMetaData = 16;
    private static final int PROPERTY_cursor = 17;
    private static final int PROPERTY_depthTest = 18;
    private static final int PROPERTY_disable = 19;
    private static final int PROPERTY_disabled = 20;
    private static final int PROPERTY_effect = 21;
    private static final int PROPERTY_effectiveNodeOrientation = 22;
    private static final int PROPERTY_eventDispatcher = 23;
    private static final int PROPERTY_focused = 24;
    private static final int PROPERTY_focusTraversable = 25;
    private static final int PROPERTY_height = 26;
    private static final int PROPERTY_hover = 27;
    private static final int PROPERTY_id = 28;
    private static final int PROPERTY_impl_traversalEngine = 29;
    private static final int PROPERTY_inputMethodRequests = 30;
    private static final int PROPERTY_insets = 31;
    private static final int PROPERTY_layoutBounds = 32;
    private static final int PROPERTY_layoutX = 33;
    private static final int PROPERTY_layoutY = 34;
    private static final int PROPERTY_left = 35;
    private static final int PROPERTY_localToParentTransform = 36;
    private static final int PROPERTY_localToSceneTransform = 37;
    private static final int PROPERTY_managed = 38;
    private static final int PROPERTY_maxHeight = 39;
    private static final int PROPERTY_maxWidth = 40;
    private static final int PROPERTY_minHeight = 41;
    private static final int PROPERTY_minWidth = 42;
    private static final int PROPERTY_mouseTransparent = 43;
    private static final int PROPERTY_needsLayout = 44;
    private static final int PROPERTY_nodeOrientation = 45;
    private static final int PROPERTY_onContextMenuRequested = 46;
    private static final int PROPERTY_onDragDetected = 47;
    private static final int PROPERTY_onDragDone = 48;
    private static final int PROPERTY_onDragDropped = 49;
    private static final int PROPERTY_onDragEntered = 50;
    private static final int PROPERTY_onDragExited = 51;
    private static final int PROPERTY_onDragOver = 52;
    private static final int PROPERTY_onInputMethodTextChanged = 53;
    private static final int PROPERTY_onKeyPressed = 54;
    private static final int PROPERTY_onKeyReleased = 55;
    private static final int PROPERTY_onKeyTyped = 56;
    private static final int PROPERTY_onMouseClicked = 57;
    private static final int PROPERTY_onMouseDragEntered = 58;
    private static final int PROPERTY_onMouseDragExited = 59;
    private static final int PROPERTY_onMouseDragged = 60;
    private static final int PROPERTY_onMouseDragOver = 61;
    private static final int PROPERTY_onMouseDragReleased = 62;
    private static final int PROPERTY_onMouseEntered = 63;
    private static final int PROPERTY_onMouseExited = 64;
    private static final int PROPERTY_onMouseMoved = 65;
    private static final int PROPERTY_onMousePressed = 66;
    private static final int PROPERTY_onMouseReleased = 67;
    private static final int PROPERTY_onRotate = 68;
    private static final int PROPERTY_onRotationFinished = 69;
    private static final int PROPERTY_onRotationStarted = 70;
    private static final int PROPERTY_onScroll = 71;
    private static final int PROPERTY_onScrollFinished = 72;
    private static final int PROPERTY_onScrollStarted = 73;
    private static final int PROPERTY_onSwipeDown = 74;
    private static final int PROPERTY_onSwipeLeft = 75;
    private static final int PROPERTY_onSwipeRight = 76;
    private static final int PROPERTY_onSwipeUp = 77;
    private static final int PROPERTY_onTouchMoved = 78;
    private static final int PROPERTY_onTouchPressed = 79;
    private static final int PROPERTY_onTouchReleased = 80;
    private static final int PROPERTY_onTouchStationary = 81;
    private static final int PROPERTY_onZoom = 82;
    private static final int PROPERTY_onZoomFinished = 83;
    private static final int PROPERTY_onZoomStarted = 84;
    private static final int PROPERTY_opacity = 85;
    private static final int PROPERTY_opaqueInsets = 86;
    private static final int PROPERTY_padding = 87;
    private static final int PROPERTY_parent = 88;
    private static final int PROPERTY_pickOnBounds = 89;
    private static final int PROPERTY_prefHeight = 90;
    private static final int PROPERTY_prefWidth = 91;
    private static final int PROPERTY_pressed = 92;
    private static final int PROPERTY_properties = 93;
    private static final int PROPERTY_pseudoClassStates = 94;
    private static final int PROPERTY_resizable = 95;
    private static final int PROPERTY_right = 96;
    private static final int PROPERTY_rotate = 97;
    private static final int PROPERTY_rotationAxis = 98;
    private static final int PROPERTY_scaleShape = 99;
    private static final int PROPERTY_scaleX = 100;
    private static final int PROPERTY_scaleY = 101;
    private static final int PROPERTY_scaleZ = 102;
    private static final int PROPERTY_scene = 103;
    private static final int PROPERTY_shape = 104;
    private static final int PROPERTY_snapToPixel = 105;
    private static final int PROPERTY_style = 106;
    private static final int PROPERTY_styleableParent = 107;
    private static final int PROPERTY_styleClass = 108;
    private static final int PROPERTY_stylesheets = 109;
    private static final int PROPERTY_top = 110;
    private static final int PROPERTY_transforms = 111;
    private static final int PROPERTY_translateX = 112;
    private static final int PROPERTY_translateY = 113;
    private static final int PROPERTY_translateZ = 114;
    private static final int PROPERTY_typeSelector = 115;
    private static final int PROPERTY_userData = 116;
    private static final int PROPERTY_visible = 117;
    private static final int PROPERTY_width = 118;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[119];
    
        try {
            properties[PROPERTY_background] = new PropertyDescriptor ( "background", bpm.finder.MediaControl.class, "getBackground", "setBackground" ); // NOI18N
            properties[PROPERTY_baselineOffset] = new PropertyDescriptor ( "baselineOffset", bpm.finder.MediaControl.class, "getBaselineOffset", null ); // NOI18N
            properties[PROPERTY_blendMode] = new PropertyDescriptor ( "blendMode", bpm.finder.MediaControl.class, "getBlendMode", "setBlendMode" ); // NOI18N
            properties[PROPERTY_border] = new PropertyDescriptor ( "border", bpm.finder.MediaControl.class, "getBorder", "setBorder" ); // NOI18N
            properties[PROPERTY_bottom] = new PropertyDescriptor ( "bottom", bpm.finder.MediaControl.class, "getBottom", "setBottom" ); // NOI18N
            properties[PROPERTY_boundsInLocal] = new PropertyDescriptor ( "boundsInLocal", bpm.finder.MediaControl.class, "getBoundsInLocal", null ); // NOI18N
            properties[PROPERTY_boundsInParent] = new PropertyDescriptor ( "boundsInParent", bpm.finder.MediaControl.class, "getBoundsInParent", null ); // NOI18N
            properties[PROPERTY_cache] = new PropertyDescriptor ( "cache", bpm.finder.MediaControl.class, "isCache", "setCache" ); // NOI18N
            properties[PROPERTY_cacheHint] = new PropertyDescriptor ( "cacheHint", bpm.finder.MediaControl.class, "getCacheHint", "setCacheHint" ); // NOI18N
            properties[PROPERTY_cacheShape] = new PropertyDescriptor ( "cacheShape", bpm.finder.MediaControl.class, "isCacheShape", "setCacheShape" ); // NOI18N
            properties[PROPERTY_center] = new PropertyDescriptor ( "center", bpm.finder.MediaControl.class, "getCenter", "setCenter" ); // NOI18N
            properties[PROPERTY_centerShape] = new PropertyDescriptor ( "centerShape", bpm.finder.MediaControl.class, "isCenterShape", "setCenterShape" ); // NOI18N
            properties[PROPERTY_children] = new PropertyDescriptor ( "children", bpm.finder.MediaControl.class, "getChildren", null ); // NOI18N
            properties[PROPERTY_childrenUnmodifiable] = new PropertyDescriptor ( "childrenUnmodifiable", bpm.finder.MediaControl.class, "getChildrenUnmodifiable", null ); // NOI18N
            properties[PROPERTY_clip] = new PropertyDescriptor ( "clip", bpm.finder.MediaControl.class, "getClip", "setClip" ); // NOI18N
            properties[PROPERTY_contentBias] = new PropertyDescriptor ( "contentBias", bpm.finder.MediaControl.class, "getContentBias", null ); // NOI18N
            properties[PROPERTY_cssMetaData] = new PropertyDescriptor ( "cssMetaData", bpm.finder.MediaControl.class, "getCssMetaData", null ); // NOI18N
            properties[PROPERTY_cursor] = new PropertyDescriptor ( "cursor", bpm.finder.MediaControl.class, "getCursor", "setCursor" ); // NOI18N
            properties[PROPERTY_depthTest] = new PropertyDescriptor ( "depthTest", bpm.finder.MediaControl.class, "getDepthTest", "setDepthTest" ); // NOI18N
            properties[PROPERTY_disable] = new PropertyDescriptor ( "disable", bpm.finder.MediaControl.class, "isDisable", "setDisable" ); // NOI18N
            properties[PROPERTY_disabled] = new PropertyDescriptor ( "disabled", bpm.finder.MediaControl.class, "isDisabled", null ); // NOI18N
            properties[PROPERTY_effect] = new PropertyDescriptor ( "effect", bpm.finder.MediaControl.class, "getEffect", "setEffect" ); // NOI18N
            properties[PROPERTY_effectiveNodeOrientation] = new PropertyDescriptor ( "effectiveNodeOrientation", bpm.finder.MediaControl.class, "getEffectiveNodeOrientation", null ); // NOI18N
            properties[PROPERTY_eventDispatcher] = new PropertyDescriptor ( "eventDispatcher", bpm.finder.MediaControl.class, "getEventDispatcher", "setEventDispatcher" ); // NOI18N
            properties[PROPERTY_focused] = new PropertyDescriptor ( "focused", bpm.finder.MediaControl.class, "isFocused", null ); // NOI18N
            properties[PROPERTY_focusTraversable] = new PropertyDescriptor ( "focusTraversable", bpm.finder.MediaControl.class, "isFocusTraversable", "setFocusTraversable" ); // NOI18N
            properties[PROPERTY_height] = new PropertyDescriptor ( "height", bpm.finder.MediaControl.class, "getHeight", null ); // NOI18N
            properties[PROPERTY_hover] = new PropertyDescriptor ( "hover", bpm.finder.MediaControl.class, "isHover", null ); // NOI18N
            properties[PROPERTY_id] = new PropertyDescriptor ( "id", bpm.finder.MediaControl.class, "getId", "setId" ); // NOI18N
            properties[PROPERTY_impl_traversalEngine] = new PropertyDescriptor ( "impl_traversalEngine", bpm.finder.MediaControl.class, "getImpl_traversalEngine", "setImpl_traversalEngine" ); // NOI18N
            properties[PROPERTY_inputMethodRequests] = new PropertyDescriptor ( "inputMethodRequests", bpm.finder.MediaControl.class, "getInputMethodRequests", "setInputMethodRequests" ); // NOI18N
            properties[PROPERTY_insets] = new PropertyDescriptor ( "insets", bpm.finder.MediaControl.class, "getInsets", null ); // NOI18N
            properties[PROPERTY_layoutBounds] = new PropertyDescriptor ( "layoutBounds", bpm.finder.MediaControl.class, "getLayoutBounds", null ); // NOI18N
            properties[PROPERTY_layoutX] = new PropertyDescriptor ( "layoutX", bpm.finder.MediaControl.class, "getLayoutX", "setLayoutX" ); // NOI18N
            properties[PROPERTY_layoutY] = new PropertyDescriptor ( "layoutY", bpm.finder.MediaControl.class, "getLayoutY", "setLayoutY" ); // NOI18N
            properties[PROPERTY_left] = new PropertyDescriptor ( "left", bpm.finder.MediaControl.class, "getLeft", "setLeft" ); // NOI18N
            properties[PROPERTY_localToParentTransform] = new PropertyDescriptor ( "localToParentTransform", bpm.finder.MediaControl.class, "getLocalToParentTransform", null ); // NOI18N
            properties[PROPERTY_localToSceneTransform] = new PropertyDescriptor ( "localToSceneTransform", bpm.finder.MediaControl.class, "getLocalToSceneTransform", null ); // NOI18N
            properties[PROPERTY_managed] = new PropertyDescriptor ( "managed", bpm.finder.MediaControl.class, "isManaged", "setManaged" ); // NOI18N
            properties[PROPERTY_maxHeight] = new PropertyDescriptor ( "maxHeight", bpm.finder.MediaControl.class, "getMaxHeight", "setMaxHeight" ); // NOI18N
            properties[PROPERTY_maxWidth] = new PropertyDescriptor ( "maxWidth", bpm.finder.MediaControl.class, "getMaxWidth", "setMaxWidth" ); // NOI18N
            properties[PROPERTY_minHeight] = new PropertyDescriptor ( "minHeight", bpm.finder.MediaControl.class, "getMinHeight", "setMinHeight" ); // NOI18N
            properties[PROPERTY_minWidth] = new PropertyDescriptor ( "minWidth", bpm.finder.MediaControl.class, "getMinWidth", "setMinWidth" ); // NOI18N
            properties[PROPERTY_mouseTransparent] = new PropertyDescriptor ( "mouseTransparent", bpm.finder.MediaControl.class, "isMouseTransparent", "setMouseTransparent" ); // NOI18N
            properties[PROPERTY_needsLayout] = new PropertyDescriptor ( "needsLayout", bpm.finder.MediaControl.class, "isNeedsLayout", null ); // NOI18N
            properties[PROPERTY_nodeOrientation] = new PropertyDescriptor ( "nodeOrientation", bpm.finder.MediaControl.class, "getNodeOrientation", "setNodeOrientation" ); // NOI18N
            properties[PROPERTY_onContextMenuRequested] = new PropertyDescriptor ( "onContextMenuRequested", bpm.finder.MediaControl.class, "getOnContextMenuRequested", "setOnContextMenuRequested" ); // NOI18N
            properties[PROPERTY_onDragDetected] = new PropertyDescriptor ( "onDragDetected", bpm.finder.MediaControl.class, "getOnDragDetected", "setOnDragDetected" ); // NOI18N
            properties[PROPERTY_onDragDone] = new PropertyDescriptor ( "onDragDone", bpm.finder.MediaControl.class, "getOnDragDone", "setOnDragDone" ); // NOI18N
            properties[PROPERTY_onDragDropped] = new PropertyDescriptor ( "onDragDropped", bpm.finder.MediaControl.class, "getOnDragDropped", "setOnDragDropped" ); // NOI18N
            properties[PROPERTY_onDragEntered] = new PropertyDescriptor ( "onDragEntered", bpm.finder.MediaControl.class, "getOnDragEntered", "setOnDragEntered" ); // NOI18N
            properties[PROPERTY_onDragExited] = new PropertyDescriptor ( "onDragExited", bpm.finder.MediaControl.class, "getOnDragExited", "setOnDragExited" ); // NOI18N
            properties[PROPERTY_onDragOver] = new PropertyDescriptor ( "onDragOver", bpm.finder.MediaControl.class, "getOnDragOver", "setOnDragOver" ); // NOI18N
            properties[PROPERTY_onInputMethodTextChanged] = new PropertyDescriptor ( "onInputMethodTextChanged", bpm.finder.MediaControl.class, "getOnInputMethodTextChanged", "setOnInputMethodTextChanged" ); // NOI18N
            properties[PROPERTY_onKeyPressed] = new PropertyDescriptor ( "onKeyPressed", bpm.finder.MediaControl.class, "getOnKeyPressed", "setOnKeyPressed" ); // NOI18N
            properties[PROPERTY_onKeyReleased] = new PropertyDescriptor ( "onKeyReleased", bpm.finder.MediaControl.class, "getOnKeyReleased", "setOnKeyReleased" ); // NOI18N
            properties[PROPERTY_onKeyTyped] = new PropertyDescriptor ( "onKeyTyped", bpm.finder.MediaControl.class, "getOnKeyTyped", "setOnKeyTyped" ); // NOI18N
            properties[PROPERTY_onMouseClicked] = new PropertyDescriptor ( "onMouseClicked", bpm.finder.MediaControl.class, "getOnMouseClicked", "setOnMouseClicked" ); // NOI18N
            properties[PROPERTY_onMouseDragEntered] = new PropertyDescriptor ( "onMouseDragEntered", bpm.finder.MediaControl.class, "getOnMouseDragEntered", "setOnMouseDragEntered" ); // NOI18N
            properties[PROPERTY_onMouseDragExited] = new PropertyDescriptor ( "onMouseDragExited", bpm.finder.MediaControl.class, "getOnMouseDragExited", "setOnMouseDragExited" ); // NOI18N
            properties[PROPERTY_onMouseDragged] = new PropertyDescriptor ( "onMouseDragged", bpm.finder.MediaControl.class, "getOnMouseDragged", "setOnMouseDragged" ); // NOI18N
            properties[PROPERTY_onMouseDragOver] = new PropertyDescriptor ( "onMouseDragOver", bpm.finder.MediaControl.class, "getOnMouseDragOver", "setOnMouseDragOver" ); // NOI18N
            properties[PROPERTY_onMouseDragReleased] = new PropertyDescriptor ( "onMouseDragReleased", bpm.finder.MediaControl.class, "getOnMouseDragReleased", "setOnMouseDragReleased" ); // NOI18N
            properties[PROPERTY_onMouseEntered] = new PropertyDescriptor ( "onMouseEntered", bpm.finder.MediaControl.class, "getOnMouseEntered", "setOnMouseEntered" ); // NOI18N
            properties[PROPERTY_onMouseExited] = new PropertyDescriptor ( "onMouseExited", bpm.finder.MediaControl.class, "getOnMouseExited", "setOnMouseExited" ); // NOI18N
            properties[PROPERTY_onMouseMoved] = new PropertyDescriptor ( "onMouseMoved", bpm.finder.MediaControl.class, "getOnMouseMoved", "setOnMouseMoved" ); // NOI18N
            properties[PROPERTY_onMousePressed] = new PropertyDescriptor ( "onMousePressed", bpm.finder.MediaControl.class, "getOnMousePressed", "setOnMousePressed" ); // NOI18N
            properties[PROPERTY_onMouseReleased] = new PropertyDescriptor ( "onMouseReleased", bpm.finder.MediaControl.class, "getOnMouseReleased", "setOnMouseReleased" ); // NOI18N
            properties[PROPERTY_onRotate] = new PropertyDescriptor ( "onRotate", bpm.finder.MediaControl.class, "getOnRotate", "setOnRotate" ); // NOI18N
            properties[PROPERTY_onRotationFinished] = new PropertyDescriptor ( "onRotationFinished", bpm.finder.MediaControl.class, "getOnRotationFinished", "setOnRotationFinished" ); // NOI18N
            properties[PROPERTY_onRotationStarted] = new PropertyDescriptor ( "onRotationStarted", bpm.finder.MediaControl.class, "getOnRotationStarted", "setOnRotationStarted" ); // NOI18N
            properties[PROPERTY_onScroll] = new PropertyDescriptor ( "onScroll", bpm.finder.MediaControl.class, "getOnScroll", "setOnScroll" ); // NOI18N
            properties[PROPERTY_onScrollFinished] = new PropertyDescriptor ( "onScrollFinished", bpm.finder.MediaControl.class, "getOnScrollFinished", "setOnScrollFinished" ); // NOI18N
            properties[PROPERTY_onScrollStarted] = new PropertyDescriptor ( "onScrollStarted", bpm.finder.MediaControl.class, "getOnScrollStarted", "setOnScrollStarted" ); // NOI18N
            properties[PROPERTY_onSwipeDown] = new PropertyDescriptor ( "onSwipeDown", bpm.finder.MediaControl.class, "getOnSwipeDown", "setOnSwipeDown" ); // NOI18N
            properties[PROPERTY_onSwipeLeft] = new PropertyDescriptor ( "onSwipeLeft", bpm.finder.MediaControl.class, "getOnSwipeLeft", "setOnSwipeLeft" ); // NOI18N
            properties[PROPERTY_onSwipeRight] = new PropertyDescriptor ( "onSwipeRight", bpm.finder.MediaControl.class, "getOnSwipeRight", "setOnSwipeRight" ); // NOI18N
            properties[PROPERTY_onSwipeUp] = new PropertyDescriptor ( "onSwipeUp", bpm.finder.MediaControl.class, "getOnSwipeUp", "setOnSwipeUp" ); // NOI18N
            properties[PROPERTY_onTouchMoved] = new PropertyDescriptor ( "onTouchMoved", bpm.finder.MediaControl.class, "getOnTouchMoved", "setOnTouchMoved" ); // NOI18N
            properties[PROPERTY_onTouchPressed] = new PropertyDescriptor ( "onTouchPressed", bpm.finder.MediaControl.class, "getOnTouchPressed", "setOnTouchPressed" ); // NOI18N
            properties[PROPERTY_onTouchReleased] = new PropertyDescriptor ( "onTouchReleased", bpm.finder.MediaControl.class, "getOnTouchReleased", "setOnTouchReleased" ); // NOI18N
            properties[PROPERTY_onTouchStationary] = new PropertyDescriptor ( "onTouchStationary", bpm.finder.MediaControl.class, "getOnTouchStationary", "setOnTouchStationary" ); // NOI18N
            properties[PROPERTY_onZoom] = new PropertyDescriptor ( "onZoom", bpm.finder.MediaControl.class, "getOnZoom", "setOnZoom" ); // NOI18N
            properties[PROPERTY_onZoomFinished] = new PropertyDescriptor ( "onZoomFinished", bpm.finder.MediaControl.class, "getOnZoomFinished", "setOnZoomFinished" ); // NOI18N
            properties[PROPERTY_onZoomStarted] = new PropertyDescriptor ( "onZoomStarted", bpm.finder.MediaControl.class, "getOnZoomStarted", "setOnZoomStarted" ); // NOI18N
            properties[PROPERTY_opacity] = new PropertyDescriptor ( "opacity", bpm.finder.MediaControl.class, "getOpacity", "setOpacity" ); // NOI18N
            properties[PROPERTY_opaqueInsets] = new PropertyDescriptor ( "opaqueInsets", bpm.finder.MediaControl.class, "getOpaqueInsets", "setOpaqueInsets" ); // NOI18N
            properties[PROPERTY_padding] = new PropertyDescriptor ( "padding", bpm.finder.MediaControl.class, "getPadding", "setPadding" ); // NOI18N
            properties[PROPERTY_parent] = new PropertyDescriptor ( "parent", bpm.finder.MediaControl.class, "getParent", null ); // NOI18N
            properties[PROPERTY_pickOnBounds] = new PropertyDescriptor ( "pickOnBounds", bpm.finder.MediaControl.class, "isPickOnBounds", "setPickOnBounds" ); // NOI18N
            properties[PROPERTY_prefHeight] = new PropertyDescriptor ( "prefHeight", bpm.finder.MediaControl.class, "getPrefHeight", "setPrefHeight" ); // NOI18N
            properties[PROPERTY_prefWidth] = new PropertyDescriptor ( "prefWidth", bpm.finder.MediaControl.class, "getPrefWidth", "setPrefWidth" ); // NOI18N
            properties[PROPERTY_pressed] = new PropertyDescriptor ( "pressed", bpm.finder.MediaControl.class, "isPressed", null ); // NOI18N
            properties[PROPERTY_properties] = new PropertyDescriptor ( "properties", bpm.finder.MediaControl.class, "getProperties", null ); // NOI18N
            properties[PROPERTY_pseudoClassStates] = new PropertyDescriptor ( "pseudoClassStates", bpm.finder.MediaControl.class, "getPseudoClassStates", null ); // NOI18N
            properties[PROPERTY_resizable] = new PropertyDescriptor ( "resizable", bpm.finder.MediaControl.class, "isResizable", null ); // NOI18N
            properties[PROPERTY_right] = new PropertyDescriptor ( "right", bpm.finder.MediaControl.class, "getRight", "setRight" ); // NOI18N
            properties[PROPERTY_rotate] = new PropertyDescriptor ( "rotate", bpm.finder.MediaControl.class, "getRotate", "setRotate" ); // NOI18N
            properties[PROPERTY_rotationAxis] = new PropertyDescriptor ( "rotationAxis", bpm.finder.MediaControl.class, "getRotationAxis", "setRotationAxis" ); // NOI18N
            properties[PROPERTY_scaleShape] = new PropertyDescriptor ( "scaleShape", bpm.finder.MediaControl.class, "isScaleShape", "setScaleShape" ); // NOI18N
            properties[PROPERTY_scaleX] = new PropertyDescriptor ( "scaleX", bpm.finder.MediaControl.class, "getScaleX", "setScaleX" ); // NOI18N
            properties[PROPERTY_scaleY] = new PropertyDescriptor ( "scaleY", bpm.finder.MediaControl.class, "getScaleY", "setScaleY" ); // NOI18N
            properties[PROPERTY_scaleZ] = new PropertyDescriptor ( "scaleZ", bpm.finder.MediaControl.class, "getScaleZ", "setScaleZ" ); // NOI18N
            properties[PROPERTY_scene] = new PropertyDescriptor ( "scene", bpm.finder.MediaControl.class, "getScene", null ); // NOI18N
            properties[PROPERTY_shape] = new PropertyDescriptor ( "shape", bpm.finder.MediaControl.class, "getShape", "setShape" ); // NOI18N
            properties[PROPERTY_snapToPixel] = new PropertyDescriptor ( "snapToPixel", bpm.finder.MediaControl.class, "isSnapToPixel", "setSnapToPixel" ); // NOI18N
            properties[PROPERTY_style] = new PropertyDescriptor ( "style", bpm.finder.MediaControl.class, "getStyle", "setStyle" ); // NOI18N
            properties[PROPERTY_styleableParent] = new PropertyDescriptor ( "styleableParent", bpm.finder.MediaControl.class, "getStyleableParent", null ); // NOI18N
            properties[PROPERTY_styleClass] = new PropertyDescriptor ( "styleClass", bpm.finder.MediaControl.class, "getStyleClass", null ); // NOI18N
            properties[PROPERTY_stylesheets] = new PropertyDescriptor ( "stylesheets", bpm.finder.MediaControl.class, "getStylesheets", null ); // NOI18N
            properties[PROPERTY_top] = new PropertyDescriptor ( "top", bpm.finder.MediaControl.class, "getTop", "setTop" ); // NOI18N
            properties[PROPERTY_transforms] = new PropertyDescriptor ( "transforms", bpm.finder.MediaControl.class, "getTransforms", null ); // NOI18N
            properties[PROPERTY_translateX] = new PropertyDescriptor ( "translateX", bpm.finder.MediaControl.class, "getTranslateX", "setTranslateX" ); // NOI18N
            properties[PROPERTY_translateY] = new PropertyDescriptor ( "translateY", bpm.finder.MediaControl.class, "getTranslateY", "setTranslateY" ); // NOI18N
            properties[PROPERTY_translateZ] = new PropertyDescriptor ( "translateZ", bpm.finder.MediaControl.class, "getTranslateZ", "setTranslateZ" ); // NOI18N
            properties[PROPERTY_typeSelector] = new PropertyDescriptor ( "typeSelector", bpm.finder.MediaControl.class, "getTypeSelector", null ); // NOI18N
            properties[PROPERTY_userData] = new PropertyDescriptor ( "userData", bpm.finder.MediaControl.class, "getUserData", "setUserData" ); // NOI18N
            properties[PROPERTY_visible] = new PropertyDescriptor ( "visible", bpm.finder.MediaControl.class, "isVisible", "setVisible" ); // NOI18N
            properties[PROPERTY_width] = new PropertyDescriptor ( "width", bpm.finder.MediaControl.class, "getWidth", null ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Properties
    // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[0];//GEN-HEADEREND:Events
    // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events

    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_addEventFilter0 = 0;
    private static final int METHOD_addEventHandler1 = 1;
    private static final int METHOD_applyCss2 = 2;
    private static final int METHOD_autosize3 = 3;
    private static final int METHOD_backgroundProperty4 = 4;
    private static final int METHOD_blendModeProperty5 = 5;
    private static final int METHOD_borderProperty6 = 6;
    private static final int METHOD_bottomProperty7 = 7;
    private static final int METHOD_boundsInLocalProperty8 = 8;
    private static final int METHOD_boundsInParentProperty9 = 9;
    private static final int METHOD_buildEventDispatchChain10 = 10;
    private static final int METHOD_cacheHintProperty11 = 11;
    private static final int METHOD_cacheProperty12 = 12;
    private static final int METHOD_cacheShapeProperty13 = 13;
    private static final int METHOD_centerProperty14 = 14;
    private static final int METHOD_centerShapeProperty15 = 15;
    private static final int METHOD_clearConstraints16 = 16;
    private static final int METHOD_clipProperty17 = 17;
    private static final int METHOD_computeAreaInScreen18 = 18;
    private static final int METHOD_contains19 = 19;
    private static final int METHOD_contains20 = 20;
    private static final int METHOD_cursorProperty21 = 21;
    private static final int METHOD_depthTestProperty22 = 22;
    private static final int METHOD_disabledProperty23 = 23;
    private static final int METHOD_disableProperty24 = 24;
    private static final int METHOD_effectiveNodeOrientationProperty25 = 25;
    private static final int METHOD_effectProperty26 = 26;
    private static final int METHOD_eventDispatcherProperty27 = 27;
    private static final int METHOD_fireEvent28 = 28;
    private static final int METHOD_focusedProperty29 = 29;
    private static final int METHOD_focusTraversableProperty30 = 30;
    private static final int METHOD_getAlignment31 = 31;
    private static final int METHOD_getClassCssMetaData32 = 32;
    private static final int METHOD_getClassCssMetaData33 = 33;
    private static final int METHOD_getMargin34 = 34;
    private static final int METHOD_hasProperties35 = 35;
    private static final int METHOD_heightProperty36 = 36;
    private static final int METHOD_hoverProperty37 = 37;
    private static final int METHOD_idProperty38 = 38;
    private static final int METHOD_impl_computeGeomBounds39 = 39;
    private static final int METHOD_impl_createPeer40 = 40;
    private static final int METHOD_impl_findStyles41 = 41;
    private static final int METHOD_impl_getAllParentStylesheets42 = 42;
    private static final int METHOD_impl_getLeafTransform43 = 43;
    private static final int METHOD_impl_getMatchingStyles44 = 44;
    private static final int METHOD_impl_getPeer45 = 45;
    private static final int METHOD_impl_getPivotX46 = 46;
    private static final int METHOD_impl_getPivotY47 = 47;
    private static final int METHOD_impl_getPivotZ48 = 48;
    private static final int METHOD_impl_getStyleMap49 = 49;
    private static final int METHOD_impl_hasTransforms50 = 50;
    private static final int METHOD_impl_isShowMnemonics51 = 51;
    private static final int METHOD_impl_isTreeVisible52 = 52;
    private static final int METHOD_impl_pickNode53 = 53;
    private static final int METHOD_impl_processCSS54 = 54;
    private static final int METHOD_impl_processMXNode55 = 55;
    private static final int METHOD_impl_reapplyCSS56 = 56;
    private static final int METHOD_impl_setShowMnemonics57 = 57;
    private static final int METHOD_impl_setStyleMap58 = 58;
    private static final int METHOD_impl_showMnemonicsProperty59 = 59;
    private static final int METHOD_impl_syncPeer60 = 60;
    private static final int METHOD_impl_transformsChanged61 = 61;
    private static final int METHOD_impl_traversalEngineProperty62 = 62;
    private static final int METHOD_impl_traverse63 = 63;
    private static final int METHOD_impl_updatePeer64 = 64;
    private static final int METHOD_inputMethodRequestsProperty65 = 65;
    private static final int METHOD_insetsProperty66 = 66;
    private static final int METHOD_intersects67 = 67;
    private static final int METHOD_intersects68 = 68;
    private static final int METHOD_layout69 = 69;
    private static final int METHOD_layoutBoundsProperty70 = 70;
    private static final int METHOD_layoutInArea71 = 71;
    private static final int METHOD_layoutXProperty72 = 72;
    private static final int METHOD_layoutYProperty73 = 73;
    private static final int METHOD_leftProperty74 = 74;
    private static final int METHOD_localToParent75 = 75;
    private static final int METHOD_localToParent76 = 76;
    private static final int METHOD_localToParent77 = 77;
    private static final int METHOD_localToParent78 = 78;
    private static final int METHOD_localToParent79 = 79;
    private static final int METHOD_localToParentTransformProperty80 = 80;
    private static final int METHOD_localToScene81 = 81;
    private static final int METHOD_localToScene82 = 82;
    private static final int METHOD_localToScene83 = 83;
    private static final int METHOD_localToScene84 = 84;
    private static final int METHOD_localToScene85 = 85;
    private static final int METHOD_localToSceneTransformProperty86 = 86;
    private static final int METHOD_localToScreen87 = 87;
    private static final int METHOD_localToScreen88 = 88;
    private static final int METHOD_localToScreen89 = 89;
    private static final int METHOD_localToScreen90 = 90;
    private static final int METHOD_localToScreen91 = 91;
    private static final int METHOD_lookup92 = 92;
    private static final int METHOD_lookupAll93 = 93;
    private static final int METHOD_managedProperty94 = 94;
    private static final int METHOD_maxHeight95 = 95;
    private static final int METHOD_maxHeightProperty96 = 96;
    private static final int METHOD_maxWidth97 = 97;
    private static final int METHOD_maxWidthProperty98 = 98;
    private static final int METHOD_minHeight99 = 99;
    private static final int METHOD_minHeightProperty100 = 100;
    private static final int METHOD_minWidth101 = 101;
    private static final int METHOD_minWidthProperty102 = 102;
    private static final int METHOD_mouseTransparentProperty103 = 103;
    private static final int METHOD_needsLayoutProperty104 = 104;
    private static final int METHOD_nodeOrientationProperty105 = 105;
    private static final int METHOD_onContextMenuRequestedProperty106 = 106;
    private static final int METHOD_onDragDetectedProperty107 = 107;
    private static final int METHOD_onDragDoneProperty108 = 108;
    private static final int METHOD_onDragDroppedProperty109 = 109;
    private static final int METHOD_onDragEnteredProperty110 = 110;
    private static final int METHOD_onDragExitedProperty111 = 111;
    private static final int METHOD_onDragOverProperty112 = 112;
    private static final int METHOD_onInputMethodTextChangedProperty113 = 113;
    private static final int METHOD_onKeyPressedProperty114 = 114;
    private static final int METHOD_onKeyReleasedProperty115 = 115;
    private static final int METHOD_onKeyTypedProperty116 = 116;
    private static final int METHOD_onMouseClickedProperty117 = 117;
    private static final int METHOD_onMouseDragEnteredProperty118 = 118;
    private static final int METHOD_onMouseDragExitedProperty119 = 119;
    private static final int METHOD_onMouseDraggedProperty120 = 120;
    private static final int METHOD_onMouseDragOverProperty121 = 121;
    private static final int METHOD_onMouseDragReleasedProperty122 = 122;
    private static final int METHOD_onMouseEnteredProperty123 = 123;
    private static final int METHOD_onMouseExitedProperty124 = 124;
    private static final int METHOD_onMouseMovedProperty125 = 125;
    private static final int METHOD_onMousePressedProperty126 = 126;
    private static final int METHOD_onMouseReleasedProperty127 = 127;
    private static final int METHOD_onRotateProperty128 = 128;
    private static final int METHOD_onRotationFinishedProperty129 = 129;
    private static final int METHOD_onRotationStartedProperty130 = 130;
    private static final int METHOD_onScrollFinishedProperty131 = 131;
    private static final int METHOD_onScrollProperty132 = 132;
    private static final int METHOD_onScrollStartedProperty133 = 133;
    private static final int METHOD_onSwipeDownProperty134 = 134;
    private static final int METHOD_onSwipeLeftProperty135 = 135;
    private static final int METHOD_onSwipeRightProperty136 = 136;
    private static final int METHOD_onSwipeUpProperty137 = 137;
    private static final int METHOD_onTouchMovedProperty138 = 138;
    private static final int METHOD_onTouchPressedProperty139 = 139;
    private static final int METHOD_onTouchReleasedProperty140 = 140;
    private static final int METHOD_onTouchStationaryProperty141 = 141;
    private static final int METHOD_onZoomFinishedProperty142 = 142;
    private static final int METHOD_onZoomProperty143 = 143;
    private static final int METHOD_onZoomStartedProperty144 = 144;
    private static final int METHOD_opacityProperty145 = 145;
    private static final int METHOD_opaqueInsetsProperty146 = 146;
    private static final int METHOD_paddingProperty147 = 147;
    private static final int METHOD_parentProperty148 = 148;
    private static final int METHOD_parentToLocal149 = 149;
    private static final int METHOD_parentToLocal150 = 150;
    private static final int METHOD_parentToLocal151 = 151;
    private static final int METHOD_parentToLocal152 = 152;
    private static final int METHOD_parentToLocal153 = 153;
    private static final int METHOD_pickOnBoundsProperty154 = 154;
    private static final int METHOD_positionInArea155 = 155;
    private static final int METHOD_prefHeight156 = 156;
    private static final int METHOD_prefHeightProperty157 = 157;
    private static final int METHOD_prefWidth158 = 158;
    private static final int METHOD_prefWidthProperty159 = 159;
    private static final int METHOD_pressedProperty160 = 160;
    private static final int METHOD_pseudoClassStateChanged161 = 161;
    private static final int METHOD_relocate162 = 162;
    private static final int METHOD_removeEventFilter163 = 163;
    private static final int METHOD_removeEventHandler164 = 164;
    private static final int METHOD_requestFocus165 = 165;
    private static final int METHOD_requestLayout166 = 166;
    private static final int METHOD_resize167 = 167;
    private static final int METHOD_resizeRelocate168 = 168;
    private static final int METHOD_rightProperty169 = 169;
    private static final int METHOD_rotateProperty170 = 170;
    private static final int METHOD_rotationAxisProperty171 = 171;
    private static final int METHOD_scaleShapeProperty172 = 172;
    private static final int METHOD_scaleXProperty173 = 173;
    private static final int METHOD_scaleYProperty174 = 174;
    private static final int METHOD_scaleZProperty175 = 175;
    private static final int METHOD_sceneProperty176 = 176;
    private static final int METHOD_sceneToLocal177 = 177;
    private static final int METHOD_sceneToLocal178 = 178;
    private static final int METHOD_sceneToLocal179 = 179;
    private static final int METHOD_sceneToLocal180 = 180;
    private static final int METHOD_sceneToLocal181 = 181;
    private static final int METHOD_screenToLocal182 = 182;
    private static final int METHOD_screenToLocal183 = 183;
    private static final int METHOD_screenToLocal184 = 184;
    private static final int METHOD_setAlignment185 = 185;
    private static final int METHOD_setMargin186 = 186;
    private static final int METHOD_setMaxSize187 = 187;
    private static final int METHOD_setMinSize188 = 188;
    private static final int METHOD_setPrefSize189 = 189;
    private static final int METHOD_shapeProperty190 = 190;
    private static final int METHOD_snappedBottomInset191 = 191;
    private static final int METHOD_snappedLeftInset192 = 192;
    private static final int METHOD_snappedRightInset193 = 193;
    private static final int METHOD_snappedTopInset194 = 194;
    private static final int METHOD_snapshot195 = 195;
    private static final int METHOD_snapshot196 = 196;
    private static final int METHOD_snapToPixelProperty197 = 197;
    private static final int METHOD_startDragAndDrop198 = 198;
    private static final int METHOD_startFullDrag199 = 199;
    private static final int METHOD_styleProperty200 = 200;
    private static final int METHOD_toBack201 = 201;
    private static final int METHOD_toFront202 = 202;
    private static final int METHOD_topProperty203 = 203;
    private static final int METHOD_toString204 = 204;
    private static final int METHOD_translateXProperty205 = 205;
    private static final int METHOD_translateYProperty206 = 206;
    private static final int METHOD_translateZProperty207 = 207;
    private static final int METHOD_usesMirroring208 = 208;
    private static final int METHOD_visibleProperty209 = 209;
    private static final int METHOD_widthProperty210 = 210;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[211];
    
        try {
            methods[METHOD_addEventFilter0] = new MethodDescriptor(javafx.scene.Node.class.getMethod("addEventFilter", new Class[] {javafx.event.EventType.class, javafx.event.EventHandler.class})); // NOI18N
            methods[METHOD_addEventFilter0].setDisplayName ( "" );
            methods[METHOD_addEventHandler1] = new MethodDescriptor(javafx.scene.Node.class.getMethod("addEventHandler", new Class[] {javafx.event.EventType.class, javafx.event.EventHandler.class})); // NOI18N
            methods[METHOD_addEventHandler1].setDisplayName ( "" );
            methods[METHOD_applyCss2] = new MethodDescriptor(javafx.scene.Node.class.getMethod("applyCss", new Class[] {})); // NOI18N
            methods[METHOD_applyCss2].setDisplayName ( "" );
            methods[METHOD_autosize3] = new MethodDescriptor(javafx.scene.Node.class.getMethod("autosize", new Class[] {})); // NOI18N
            methods[METHOD_autosize3].setDisplayName ( "" );
            methods[METHOD_backgroundProperty4] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("backgroundProperty", new Class[] {})); // NOI18N
            methods[METHOD_backgroundProperty4].setDisplayName ( "" );
            methods[METHOD_blendModeProperty5] = new MethodDescriptor(javafx.scene.Node.class.getMethod("blendModeProperty", new Class[] {})); // NOI18N
            methods[METHOD_blendModeProperty5].setDisplayName ( "" );
            methods[METHOD_borderProperty6] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("borderProperty", new Class[] {})); // NOI18N
            methods[METHOD_borderProperty6].setDisplayName ( "" );
            methods[METHOD_bottomProperty7] = new MethodDescriptor(javafx.scene.layout.BorderPane.class.getMethod("bottomProperty", new Class[] {})); // NOI18N
            methods[METHOD_bottomProperty7].setDisplayName ( "" );
            methods[METHOD_boundsInLocalProperty8] = new MethodDescriptor(javafx.scene.Node.class.getMethod("boundsInLocalProperty", new Class[] {})); // NOI18N
            methods[METHOD_boundsInLocalProperty8].setDisplayName ( "" );
            methods[METHOD_boundsInParentProperty9] = new MethodDescriptor(javafx.scene.Node.class.getMethod("boundsInParentProperty", new Class[] {})); // NOI18N
            methods[METHOD_boundsInParentProperty9].setDisplayName ( "" );
            methods[METHOD_buildEventDispatchChain10] = new MethodDescriptor(javafx.scene.Node.class.getMethod("buildEventDispatchChain", new Class[] {javafx.event.EventDispatchChain.class})); // NOI18N
            methods[METHOD_buildEventDispatchChain10].setDisplayName ( "" );
            methods[METHOD_cacheHintProperty11] = new MethodDescriptor(javafx.scene.Node.class.getMethod("cacheHintProperty", new Class[] {})); // NOI18N
            methods[METHOD_cacheHintProperty11].setDisplayName ( "" );
            methods[METHOD_cacheProperty12] = new MethodDescriptor(javafx.scene.Node.class.getMethod("cacheProperty", new Class[] {})); // NOI18N
            methods[METHOD_cacheProperty12].setDisplayName ( "" );
            methods[METHOD_cacheShapeProperty13] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("cacheShapeProperty", new Class[] {})); // NOI18N
            methods[METHOD_cacheShapeProperty13].setDisplayName ( "" );
            methods[METHOD_centerProperty14] = new MethodDescriptor(javafx.scene.layout.BorderPane.class.getMethod("centerProperty", new Class[] {})); // NOI18N
            methods[METHOD_centerProperty14].setDisplayName ( "" );
            methods[METHOD_centerShapeProperty15] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("centerShapeProperty", new Class[] {})); // NOI18N
            methods[METHOD_centerShapeProperty15].setDisplayName ( "" );
            methods[METHOD_clearConstraints16] = new MethodDescriptor(javafx.scene.layout.BorderPane.class.getMethod("clearConstraints", new Class[] {javafx.scene.Node.class})); // NOI18N
            methods[METHOD_clearConstraints16].setDisplayName ( "" );
            methods[METHOD_clipProperty17] = new MethodDescriptor(javafx.scene.Node.class.getMethod("clipProperty", new Class[] {})); // NOI18N
            methods[METHOD_clipProperty17].setDisplayName ( "" );
            methods[METHOD_computeAreaInScreen18] = new MethodDescriptor(javafx.scene.Node.class.getMethod("computeAreaInScreen", new Class[] {})); // NOI18N
            methods[METHOD_computeAreaInScreen18].setDisplayName ( "" );
            methods[METHOD_contains19] = new MethodDescriptor(javafx.scene.Node.class.getMethod("contains", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_contains19].setDisplayName ( "" );
            methods[METHOD_contains20] = new MethodDescriptor(javafx.scene.Node.class.getMethod("contains", new Class[] {javafx.geometry.Point2D.class})); // NOI18N
            methods[METHOD_contains20].setDisplayName ( "" );
            methods[METHOD_cursorProperty21] = new MethodDescriptor(javafx.scene.Node.class.getMethod("cursorProperty", new Class[] {})); // NOI18N
            methods[METHOD_cursorProperty21].setDisplayName ( "" );
            methods[METHOD_depthTestProperty22] = new MethodDescriptor(javafx.scene.Node.class.getMethod("depthTestProperty", new Class[] {})); // NOI18N
            methods[METHOD_depthTestProperty22].setDisplayName ( "" );
            methods[METHOD_disabledProperty23] = new MethodDescriptor(javafx.scene.Node.class.getMethod("disabledProperty", new Class[] {})); // NOI18N
            methods[METHOD_disabledProperty23].setDisplayName ( "" );
            methods[METHOD_disableProperty24] = new MethodDescriptor(javafx.scene.Node.class.getMethod("disableProperty", new Class[] {})); // NOI18N
            methods[METHOD_disableProperty24].setDisplayName ( "" );
            methods[METHOD_effectiveNodeOrientationProperty25] = new MethodDescriptor(javafx.scene.Node.class.getMethod("effectiveNodeOrientationProperty", new Class[] {})); // NOI18N
            methods[METHOD_effectiveNodeOrientationProperty25].setDisplayName ( "" );
            methods[METHOD_effectProperty26] = new MethodDescriptor(javafx.scene.Node.class.getMethod("effectProperty", new Class[] {})); // NOI18N
            methods[METHOD_effectProperty26].setDisplayName ( "" );
            methods[METHOD_eventDispatcherProperty27] = new MethodDescriptor(javafx.scene.Node.class.getMethod("eventDispatcherProperty", new Class[] {})); // NOI18N
            methods[METHOD_eventDispatcherProperty27].setDisplayName ( "" );
            methods[METHOD_fireEvent28] = new MethodDescriptor(javafx.scene.Node.class.getMethod("fireEvent", new Class[] {javafx.event.Event.class})); // NOI18N
            methods[METHOD_fireEvent28].setDisplayName ( "" );
            methods[METHOD_focusedProperty29] = new MethodDescriptor(javafx.scene.Node.class.getMethod("focusedProperty", new Class[] {})); // NOI18N
            methods[METHOD_focusedProperty29].setDisplayName ( "" );
            methods[METHOD_focusTraversableProperty30] = new MethodDescriptor(javafx.scene.Node.class.getMethod("focusTraversableProperty", new Class[] {})); // NOI18N
            methods[METHOD_focusTraversableProperty30].setDisplayName ( "" );
            methods[METHOD_getAlignment31] = new MethodDescriptor(javafx.scene.layout.BorderPane.class.getMethod("getAlignment", new Class[] {javafx.scene.Node.class})); // NOI18N
            methods[METHOD_getAlignment31].setDisplayName ( "" );
            methods[METHOD_getClassCssMetaData32] = new MethodDescriptor(javafx.scene.Node.class.getMethod("getClassCssMetaData", new Class[] {})); // NOI18N
            methods[METHOD_getClassCssMetaData32].setDisplayName ( "" );
            methods[METHOD_getClassCssMetaData33] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("getClassCssMetaData", new Class[] {})); // NOI18N
            methods[METHOD_getClassCssMetaData33].setDisplayName ( "" );
            methods[METHOD_getMargin34] = new MethodDescriptor(javafx.scene.layout.BorderPane.class.getMethod("getMargin", new Class[] {javafx.scene.Node.class})); // NOI18N
            methods[METHOD_getMargin34].setDisplayName ( "" );
            methods[METHOD_hasProperties35] = new MethodDescriptor(javafx.scene.Node.class.getMethod("hasProperties", new Class[] {})); // NOI18N
            methods[METHOD_hasProperties35].setDisplayName ( "" );
            methods[METHOD_heightProperty36] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("heightProperty", new Class[] {})); // NOI18N
            methods[METHOD_heightProperty36].setDisplayName ( "" );
            methods[METHOD_hoverProperty37] = new MethodDescriptor(javafx.scene.Node.class.getMethod("hoverProperty", new Class[] {})); // NOI18N
            methods[METHOD_hoverProperty37].setDisplayName ( "" );
            methods[METHOD_idProperty38] = new MethodDescriptor(javafx.scene.Node.class.getMethod("idProperty", new Class[] {})); // NOI18N
            methods[METHOD_idProperty38].setDisplayName ( "" );
            methods[METHOD_impl_computeGeomBounds39] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("impl_computeGeomBounds", new Class[] {com.sun.javafx.geom.BaseBounds.class, com.sun.javafx.geom.transform.BaseTransform.class})); // NOI18N
            methods[METHOD_impl_computeGeomBounds39].setDisplayName ( "" );
            methods[METHOD_impl_createPeer40] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("impl_createPeer", new Class[] {})); // NOI18N
            methods[METHOD_impl_createPeer40].setDisplayName ( "" );
            methods[METHOD_impl_findStyles41] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_findStyles", new Class[] {java.util.Map.class})); // NOI18N
            methods[METHOD_impl_findStyles41].setDisplayName ( "" );
            methods[METHOD_impl_getAllParentStylesheets42] = new MethodDescriptor(javafx.scene.Parent.class.getMethod("impl_getAllParentStylesheets", new Class[] {})); // NOI18N
            methods[METHOD_impl_getAllParentStylesheets42].setDisplayName ( "" );
            methods[METHOD_impl_getLeafTransform43] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_getLeafTransform", new Class[] {})); // NOI18N
            methods[METHOD_impl_getLeafTransform43].setDisplayName ( "" );
            methods[METHOD_impl_getMatchingStyles44] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_getMatchingStyles", new Class[] {javafx.css.CssMetaData.class, javafx.css.Styleable.class})); // NOI18N
            methods[METHOD_impl_getMatchingStyles44].setDisplayName ( "" );
            methods[METHOD_impl_getPeer45] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_getPeer", new Class[] {})); // NOI18N
            methods[METHOD_impl_getPeer45].setDisplayName ( "" );
            methods[METHOD_impl_getPivotX46] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_getPivotX", new Class[] {})); // NOI18N
            methods[METHOD_impl_getPivotX46].setDisplayName ( "" );
            methods[METHOD_impl_getPivotY47] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_getPivotY", new Class[] {})); // NOI18N
            methods[METHOD_impl_getPivotY47].setDisplayName ( "" );
            methods[METHOD_impl_getPivotZ48] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_getPivotZ", new Class[] {})); // NOI18N
            methods[METHOD_impl_getPivotZ48].setDisplayName ( "" );
            methods[METHOD_impl_getStyleMap49] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_getStyleMap", new Class[] {})); // NOI18N
            methods[METHOD_impl_getStyleMap49].setDisplayName ( "" );
            methods[METHOD_impl_hasTransforms50] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_hasTransforms", new Class[] {})); // NOI18N
            methods[METHOD_impl_hasTransforms50].setDisplayName ( "" );
            methods[METHOD_impl_isShowMnemonics51] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_isShowMnemonics", new Class[] {})); // NOI18N
            methods[METHOD_impl_isShowMnemonics51].setDisplayName ( "" );
            methods[METHOD_impl_isTreeVisible52] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_isTreeVisible", new Class[] {})); // NOI18N
            methods[METHOD_impl_isTreeVisible52].setDisplayName ( "" );
            methods[METHOD_impl_pickNode53] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_pickNode", new Class[] {com.sun.javafx.geom.PickRay.class, com.sun.javafx.scene.input.PickResultChooser.class})); // NOI18N
            methods[METHOD_impl_pickNode53].setDisplayName ( "" );
            methods[METHOD_impl_processCSS54] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_processCSS", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_impl_processCSS54].setDisplayName ( "" );
            methods[METHOD_impl_processMXNode55] = new MethodDescriptor(javafx.scene.Parent.class.getMethod("impl_processMXNode", new Class[] {com.sun.javafx.jmx.MXNodeAlgorithm.class, com.sun.javafx.jmx.MXNodeAlgorithmContext.class})); // NOI18N
            methods[METHOD_impl_processMXNode55].setDisplayName ( "" );
            methods[METHOD_impl_reapplyCSS56] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_reapplyCSS", new Class[] {})); // NOI18N
            methods[METHOD_impl_reapplyCSS56].setDisplayName ( "" );
            methods[METHOD_impl_setShowMnemonics57] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_setShowMnemonics", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_impl_setShowMnemonics57].setDisplayName ( "" );
            methods[METHOD_impl_setStyleMap58] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_setStyleMap", new Class[] {javafx.collections.ObservableMap.class})); // NOI18N
            methods[METHOD_impl_setStyleMap58].setDisplayName ( "" );
            methods[METHOD_impl_showMnemonicsProperty59] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_showMnemonicsProperty", new Class[] {})); // NOI18N
            methods[METHOD_impl_showMnemonicsProperty59].setDisplayName ( "" );
            methods[METHOD_impl_syncPeer60] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_syncPeer", new Class[] {})); // NOI18N
            methods[METHOD_impl_syncPeer60].setDisplayName ( "" );
            methods[METHOD_impl_transformsChanged61] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_transformsChanged", new Class[] {})); // NOI18N
            methods[METHOD_impl_transformsChanged61].setDisplayName ( "" );
            methods[METHOD_impl_traversalEngineProperty62] = new MethodDescriptor(javafx.scene.Parent.class.getMethod("impl_traversalEngineProperty", new Class[] {})); // NOI18N
            methods[METHOD_impl_traversalEngineProperty62].setDisplayName ( "" );
            methods[METHOD_impl_traverse63] = new MethodDescriptor(javafx.scene.Node.class.getMethod("impl_traverse", new Class[] {com.sun.javafx.scene.traversal.Direction.class})); // NOI18N
            methods[METHOD_impl_traverse63].setDisplayName ( "" );
            methods[METHOD_impl_updatePeer64] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("impl_updatePeer", new Class[] {})); // NOI18N
            methods[METHOD_impl_updatePeer64].setDisplayName ( "" );
            methods[METHOD_inputMethodRequestsProperty65] = new MethodDescriptor(javafx.scene.Node.class.getMethod("inputMethodRequestsProperty", new Class[] {})); // NOI18N
            methods[METHOD_inputMethodRequestsProperty65].setDisplayName ( "" );
            methods[METHOD_insetsProperty66] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("insetsProperty", new Class[] {})); // NOI18N
            methods[METHOD_insetsProperty66].setDisplayName ( "" );
            methods[METHOD_intersects67] = new MethodDescriptor(javafx.scene.Node.class.getMethod("intersects", new Class[] {double.class, double.class, double.class, double.class})); // NOI18N
            methods[METHOD_intersects67].setDisplayName ( "" );
            methods[METHOD_intersects68] = new MethodDescriptor(javafx.scene.Node.class.getMethod("intersects", new Class[] {javafx.geometry.Bounds.class})); // NOI18N
            methods[METHOD_intersects68].setDisplayName ( "" );
            methods[METHOD_layout69] = new MethodDescriptor(javafx.scene.Parent.class.getMethod("layout", new Class[] {})); // NOI18N
            methods[METHOD_layout69].setDisplayName ( "" );
            methods[METHOD_layoutBoundsProperty70] = new MethodDescriptor(javafx.scene.Node.class.getMethod("layoutBoundsProperty", new Class[] {})); // NOI18N
            methods[METHOD_layoutBoundsProperty70].setDisplayName ( "" );
            methods[METHOD_layoutInArea71] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("layoutInArea", new Class[] {javafx.scene.Node.class, double.class, double.class, double.class, double.class, double.class, javafx.geometry.Insets.class, boolean.class, boolean.class, javafx.geometry.HPos.class, javafx.geometry.VPos.class, boolean.class})); // NOI18N
            methods[METHOD_layoutInArea71].setDisplayName ( "" );
            methods[METHOD_layoutXProperty72] = new MethodDescriptor(javafx.scene.Node.class.getMethod("layoutXProperty", new Class[] {})); // NOI18N
            methods[METHOD_layoutXProperty72].setDisplayName ( "" );
            methods[METHOD_layoutYProperty73] = new MethodDescriptor(javafx.scene.Node.class.getMethod("layoutYProperty", new Class[] {})); // NOI18N
            methods[METHOD_layoutYProperty73].setDisplayName ( "" );
            methods[METHOD_leftProperty74] = new MethodDescriptor(javafx.scene.layout.BorderPane.class.getMethod("leftProperty", new Class[] {})); // NOI18N
            methods[METHOD_leftProperty74].setDisplayName ( "" );
            methods[METHOD_localToParent75] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToParent", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_localToParent75].setDisplayName ( "" );
            methods[METHOD_localToParent76] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToParent", new Class[] {javafx.geometry.Point2D.class})); // NOI18N
            methods[METHOD_localToParent76].setDisplayName ( "" );
            methods[METHOD_localToParent77] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToParent", new Class[] {javafx.geometry.Point3D.class})); // NOI18N
            methods[METHOD_localToParent77].setDisplayName ( "" );
            methods[METHOD_localToParent78] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToParent", new Class[] {double.class, double.class, double.class})); // NOI18N
            methods[METHOD_localToParent78].setDisplayName ( "" );
            methods[METHOD_localToParent79] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToParent", new Class[] {javafx.geometry.Bounds.class})); // NOI18N
            methods[METHOD_localToParent79].setDisplayName ( "" );
            methods[METHOD_localToParentTransformProperty80] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToParentTransformProperty", new Class[] {})); // NOI18N
            methods[METHOD_localToParentTransformProperty80].setDisplayName ( "" );
            methods[METHOD_localToScene81] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToScene", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_localToScene81].setDisplayName ( "" );
            methods[METHOD_localToScene82] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToScene", new Class[] {javafx.geometry.Point2D.class})); // NOI18N
            methods[METHOD_localToScene82].setDisplayName ( "" );
            methods[METHOD_localToScene83] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToScene", new Class[] {javafx.geometry.Point3D.class})); // NOI18N
            methods[METHOD_localToScene83].setDisplayName ( "" );
            methods[METHOD_localToScene84] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToScene", new Class[] {double.class, double.class, double.class})); // NOI18N
            methods[METHOD_localToScene84].setDisplayName ( "" );
            methods[METHOD_localToScene85] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToScene", new Class[] {javafx.geometry.Bounds.class})); // NOI18N
            methods[METHOD_localToScene85].setDisplayName ( "" );
            methods[METHOD_localToSceneTransformProperty86] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToSceneTransformProperty", new Class[] {})); // NOI18N
            methods[METHOD_localToSceneTransformProperty86].setDisplayName ( "" );
            methods[METHOD_localToScreen87] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToScreen", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_localToScreen87].setDisplayName ( "" );
            methods[METHOD_localToScreen88] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToScreen", new Class[] {javafx.geometry.Point2D.class})); // NOI18N
            methods[METHOD_localToScreen88].setDisplayName ( "" );
            methods[METHOD_localToScreen89] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToScreen", new Class[] {double.class, double.class, double.class})); // NOI18N
            methods[METHOD_localToScreen89].setDisplayName ( "" );
            methods[METHOD_localToScreen90] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToScreen", new Class[] {javafx.geometry.Point3D.class})); // NOI18N
            methods[METHOD_localToScreen90].setDisplayName ( "" );
            methods[METHOD_localToScreen91] = new MethodDescriptor(javafx.scene.Node.class.getMethod("localToScreen", new Class[] {javafx.geometry.Bounds.class})); // NOI18N
            methods[METHOD_localToScreen91].setDisplayName ( "" );
            methods[METHOD_lookup92] = new MethodDescriptor(javafx.scene.Parent.class.getMethod("lookup", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_lookup92].setDisplayName ( "" );
            methods[METHOD_lookupAll93] = new MethodDescriptor(javafx.scene.Node.class.getMethod("lookupAll", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_lookupAll93].setDisplayName ( "" );
            methods[METHOD_managedProperty94] = new MethodDescriptor(javafx.scene.Node.class.getMethod("managedProperty", new Class[] {})); // NOI18N
            methods[METHOD_managedProperty94].setDisplayName ( "" );
            methods[METHOD_maxHeight95] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("maxHeight", new Class[] {double.class})); // NOI18N
            methods[METHOD_maxHeight95].setDisplayName ( "" );
            methods[METHOD_maxHeightProperty96] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("maxHeightProperty", new Class[] {})); // NOI18N
            methods[METHOD_maxHeightProperty96].setDisplayName ( "" );
            methods[METHOD_maxWidth97] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("maxWidth", new Class[] {double.class})); // NOI18N
            methods[METHOD_maxWidth97].setDisplayName ( "" );
            methods[METHOD_maxWidthProperty98] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("maxWidthProperty", new Class[] {})); // NOI18N
            methods[METHOD_maxWidthProperty98].setDisplayName ( "" );
            methods[METHOD_minHeight99] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("minHeight", new Class[] {double.class})); // NOI18N
            methods[METHOD_minHeight99].setDisplayName ( "" );
            methods[METHOD_minHeightProperty100] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("minHeightProperty", new Class[] {})); // NOI18N
            methods[METHOD_minHeightProperty100].setDisplayName ( "" );
            methods[METHOD_minWidth101] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("minWidth", new Class[] {double.class})); // NOI18N
            methods[METHOD_minWidth101].setDisplayName ( "" );
            methods[METHOD_minWidthProperty102] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("minWidthProperty", new Class[] {})); // NOI18N
            methods[METHOD_minWidthProperty102].setDisplayName ( "" );
            methods[METHOD_mouseTransparentProperty103] = new MethodDescriptor(javafx.scene.Node.class.getMethod("mouseTransparentProperty", new Class[] {})); // NOI18N
            methods[METHOD_mouseTransparentProperty103].setDisplayName ( "" );
            methods[METHOD_needsLayoutProperty104] = new MethodDescriptor(javafx.scene.Parent.class.getMethod("needsLayoutProperty", new Class[] {})); // NOI18N
            methods[METHOD_needsLayoutProperty104].setDisplayName ( "" );
            methods[METHOD_nodeOrientationProperty105] = new MethodDescriptor(javafx.scene.Node.class.getMethod("nodeOrientationProperty", new Class[] {})); // NOI18N
            methods[METHOD_nodeOrientationProperty105].setDisplayName ( "" );
            methods[METHOD_onContextMenuRequestedProperty106] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onContextMenuRequestedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onContextMenuRequestedProperty106].setDisplayName ( "" );
            methods[METHOD_onDragDetectedProperty107] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onDragDetectedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onDragDetectedProperty107].setDisplayName ( "" );
            methods[METHOD_onDragDoneProperty108] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onDragDoneProperty", new Class[] {})); // NOI18N
            methods[METHOD_onDragDoneProperty108].setDisplayName ( "" );
            methods[METHOD_onDragDroppedProperty109] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onDragDroppedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onDragDroppedProperty109].setDisplayName ( "" );
            methods[METHOD_onDragEnteredProperty110] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onDragEnteredProperty", new Class[] {})); // NOI18N
            methods[METHOD_onDragEnteredProperty110].setDisplayName ( "" );
            methods[METHOD_onDragExitedProperty111] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onDragExitedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onDragExitedProperty111].setDisplayName ( "" );
            methods[METHOD_onDragOverProperty112] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onDragOverProperty", new Class[] {})); // NOI18N
            methods[METHOD_onDragOverProperty112].setDisplayName ( "" );
            methods[METHOD_onInputMethodTextChangedProperty113] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onInputMethodTextChangedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onInputMethodTextChangedProperty113].setDisplayName ( "" );
            methods[METHOD_onKeyPressedProperty114] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onKeyPressedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onKeyPressedProperty114].setDisplayName ( "" );
            methods[METHOD_onKeyReleasedProperty115] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onKeyReleasedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onKeyReleasedProperty115].setDisplayName ( "" );
            methods[METHOD_onKeyTypedProperty116] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onKeyTypedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onKeyTypedProperty116].setDisplayName ( "" );
            methods[METHOD_onMouseClickedProperty117] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onMouseClickedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onMouseClickedProperty117].setDisplayName ( "" );
            methods[METHOD_onMouseDragEnteredProperty118] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onMouseDragEnteredProperty", new Class[] {})); // NOI18N
            methods[METHOD_onMouseDragEnteredProperty118].setDisplayName ( "" );
            methods[METHOD_onMouseDragExitedProperty119] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onMouseDragExitedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onMouseDragExitedProperty119].setDisplayName ( "" );
            methods[METHOD_onMouseDraggedProperty120] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onMouseDraggedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onMouseDraggedProperty120].setDisplayName ( "" );
            methods[METHOD_onMouseDragOverProperty121] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onMouseDragOverProperty", new Class[] {})); // NOI18N
            methods[METHOD_onMouseDragOverProperty121].setDisplayName ( "" );
            methods[METHOD_onMouseDragReleasedProperty122] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onMouseDragReleasedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onMouseDragReleasedProperty122].setDisplayName ( "" );
            methods[METHOD_onMouseEnteredProperty123] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onMouseEnteredProperty", new Class[] {})); // NOI18N
            methods[METHOD_onMouseEnteredProperty123].setDisplayName ( "" );
            methods[METHOD_onMouseExitedProperty124] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onMouseExitedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onMouseExitedProperty124].setDisplayName ( "" );
            methods[METHOD_onMouseMovedProperty125] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onMouseMovedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onMouseMovedProperty125].setDisplayName ( "" );
            methods[METHOD_onMousePressedProperty126] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onMousePressedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onMousePressedProperty126].setDisplayName ( "" );
            methods[METHOD_onMouseReleasedProperty127] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onMouseReleasedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onMouseReleasedProperty127].setDisplayName ( "" );
            methods[METHOD_onRotateProperty128] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onRotateProperty", new Class[] {})); // NOI18N
            methods[METHOD_onRotateProperty128].setDisplayName ( "" );
            methods[METHOD_onRotationFinishedProperty129] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onRotationFinishedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onRotationFinishedProperty129].setDisplayName ( "" );
            methods[METHOD_onRotationStartedProperty130] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onRotationStartedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onRotationStartedProperty130].setDisplayName ( "" );
            methods[METHOD_onScrollFinishedProperty131] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onScrollFinishedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onScrollFinishedProperty131].setDisplayName ( "" );
            methods[METHOD_onScrollProperty132] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onScrollProperty", new Class[] {})); // NOI18N
            methods[METHOD_onScrollProperty132].setDisplayName ( "" );
            methods[METHOD_onScrollStartedProperty133] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onScrollStartedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onScrollStartedProperty133].setDisplayName ( "" );
            methods[METHOD_onSwipeDownProperty134] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onSwipeDownProperty", new Class[] {})); // NOI18N
            methods[METHOD_onSwipeDownProperty134].setDisplayName ( "" );
            methods[METHOD_onSwipeLeftProperty135] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onSwipeLeftProperty", new Class[] {})); // NOI18N
            methods[METHOD_onSwipeLeftProperty135].setDisplayName ( "" );
            methods[METHOD_onSwipeRightProperty136] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onSwipeRightProperty", new Class[] {})); // NOI18N
            methods[METHOD_onSwipeRightProperty136].setDisplayName ( "" );
            methods[METHOD_onSwipeUpProperty137] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onSwipeUpProperty", new Class[] {})); // NOI18N
            methods[METHOD_onSwipeUpProperty137].setDisplayName ( "" );
            methods[METHOD_onTouchMovedProperty138] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onTouchMovedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onTouchMovedProperty138].setDisplayName ( "" );
            methods[METHOD_onTouchPressedProperty139] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onTouchPressedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onTouchPressedProperty139].setDisplayName ( "" );
            methods[METHOD_onTouchReleasedProperty140] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onTouchReleasedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onTouchReleasedProperty140].setDisplayName ( "" );
            methods[METHOD_onTouchStationaryProperty141] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onTouchStationaryProperty", new Class[] {})); // NOI18N
            methods[METHOD_onTouchStationaryProperty141].setDisplayName ( "" );
            methods[METHOD_onZoomFinishedProperty142] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onZoomFinishedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onZoomFinishedProperty142].setDisplayName ( "" );
            methods[METHOD_onZoomProperty143] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onZoomProperty", new Class[] {})); // NOI18N
            methods[METHOD_onZoomProperty143].setDisplayName ( "" );
            methods[METHOD_onZoomStartedProperty144] = new MethodDescriptor(javafx.scene.Node.class.getMethod("onZoomStartedProperty", new Class[] {})); // NOI18N
            methods[METHOD_onZoomStartedProperty144].setDisplayName ( "" );
            methods[METHOD_opacityProperty145] = new MethodDescriptor(javafx.scene.Node.class.getMethod("opacityProperty", new Class[] {})); // NOI18N
            methods[METHOD_opacityProperty145].setDisplayName ( "" );
            methods[METHOD_opaqueInsetsProperty146] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("opaqueInsetsProperty", new Class[] {})); // NOI18N
            methods[METHOD_opaqueInsetsProperty146].setDisplayName ( "" );
            methods[METHOD_paddingProperty147] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("paddingProperty", new Class[] {})); // NOI18N
            methods[METHOD_paddingProperty147].setDisplayName ( "" );
            methods[METHOD_parentProperty148] = new MethodDescriptor(javafx.scene.Node.class.getMethod("parentProperty", new Class[] {})); // NOI18N
            methods[METHOD_parentProperty148].setDisplayName ( "" );
            methods[METHOD_parentToLocal149] = new MethodDescriptor(javafx.scene.Node.class.getMethod("parentToLocal", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_parentToLocal149].setDisplayName ( "" );
            methods[METHOD_parentToLocal150] = new MethodDescriptor(javafx.scene.Node.class.getMethod("parentToLocal", new Class[] {javafx.geometry.Point2D.class})); // NOI18N
            methods[METHOD_parentToLocal150].setDisplayName ( "" );
            methods[METHOD_parentToLocal151] = new MethodDescriptor(javafx.scene.Node.class.getMethod("parentToLocal", new Class[] {javafx.geometry.Point3D.class})); // NOI18N
            methods[METHOD_parentToLocal151].setDisplayName ( "" );
            methods[METHOD_parentToLocal152] = new MethodDescriptor(javafx.scene.Node.class.getMethod("parentToLocal", new Class[] {double.class, double.class, double.class})); // NOI18N
            methods[METHOD_parentToLocal152].setDisplayName ( "" );
            methods[METHOD_parentToLocal153] = new MethodDescriptor(javafx.scene.Node.class.getMethod("parentToLocal", new Class[] {javafx.geometry.Bounds.class})); // NOI18N
            methods[METHOD_parentToLocal153].setDisplayName ( "" );
            methods[METHOD_pickOnBoundsProperty154] = new MethodDescriptor(javafx.scene.Node.class.getMethod("pickOnBoundsProperty", new Class[] {})); // NOI18N
            methods[METHOD_pickOnBoundsProperty154].setDisplayName ( "" );
            methods[METHOD_positionInArea155] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("positionInArea", new Class[] {javafx.scene.Node.class, double.class, double.class, double.class, double.class, double.class, javafx.geometry.Insets.class, javafx.geometry.HPos.class, javafx.geometry.VPos.class, boolean.class})); // NOI18N
            methods[METHOD_positionInArea155].setDisplayName ( "" );
            methods[METHOD_prefHeight156] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("prefHeight", new Class[] {double.class})); // NOI18N
            methods[METHOD_prefHeight156].setDisplayName ( "" );
            methods[METHOD_prefHeightProperty157] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("prefHeightProperty", new Class[] {})); // NOI18N
            methods[METHOD_prefHeightProperty157].setDisplayName ( "" );
            methods[METHOD_prefWidth158] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("prefWidth", new Class[] {double.class})); // NOI18N
            methods[METHOD_prefWidth158].setDisplayName ( "" );
            methods[METHOD_prefWidthProperty159] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("prefWidthProperty", new Class[] {})); // NOI18N
            methods[METHOD_prefWidthProperty159].setDisplayName ( "" );
            methods[METHOD_pressedProperty160] = new MethodDescriptor(javafx.scene.Node.class.getMethod("pressedProperty", new Class[] {})); // NOI18N
            methods[METHOD_pressedProperty160].setDisplayName ( "" );
            methods[METHOD_pseudoClassStateChanged161] = new MethodDescriptor(javafx.scene.Node.class.getMethod("pseudoClassStateChanged", new Class[] {javafx.css.PseudoClass.class, boolean.class})); // NOI18N
            methods[METHOD_pseudoClassStateChanged161].setDisplayName ( "" );
            methods[METHOD_relocate162] = new MethodDescriptor(javafx.scene.Node.class.getMethod("relocate", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_relocate162].setDisplayName ( "" );
            methods[METHOD_removeEventFilter163] = new MethodDescriptor(javafx.scene.Node.class.getMethod("removeEventFilter", new Class[] {javafx.event.EventType.class, javafx.event.EventHandler.class})); // NOI18N
            methods[METHOD_removeEventFilter163].setDisplayName ( "" );
            methods[METHOD_removeEventHandler164] = new MethodDescriptor(javafx.scene.Node.class.getMethod("removeEventHandler", new Class[] {javafx.event.EventType.class, javafx.event.EventHandler.class})); // NOI18N
            methods[METHOD_removeEventHandler164].setDisplayName ( "" );
            methods[METHOD_requestFocus165] = new MethodDescriptor(javafx.scene.Node.class.getMethod("requestFocus", new Class[] {})); // NOI18N
            methods[METHOD_requestFocus165].setDisplayName ( "" );
            methods[METHOD_requestLayout166] = new MethodDescriptor(javafx.scene.Parent.class.getMethod("requestLayout", new Class[] {})); // NOI18N
            methods[METHOD_requestLayout166].setDisplayName ( "" );
            methods[METHOD_resize167] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("resize", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_resize167].setDisplayName ( "" );
            methods[METHOD_resizeRelocate168] = new MethodDescriptor(javafx.scene.Node.class.getMethod("resizeRelocate", new Class[] {double.class, double.class, double.class, double.class})); // NOI18N
            methods[METHOD_resizeRelocate168].setDisplayName ( "" );
            methods[METHOD_rightProperty169] = new MethodDescriptor(javafx.scene.layout.BorderPane.class.getMethod("rightProperty", new Class[] {})); // NOI18N
            methods[METHOD_rightProperty169].setDisplayName ( "" );
            methods[METHOD_rotateProperty170] = new MethodDescriptor(javafx.scene.Node.class.getMethod("rotateProperty", new Class[] {})); // NOI18N
            methods[METHOD_rotateProperty170].setDisplayName ( "" );
            methods[METHOD_rotationAxisProperty171] = new MethodDescriptor(javafx.scene.Node.class.getMethod("rotationAxisProperty", new Class[] {})); // NOI18N
            methods[METHOD_rotationAxisProperty171].setDisplayName ( "" );
            methods[METHOD_scaleShapeProperty172] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("scaleShapeProperty", new Class[] {})); // NOI18N
            methods[METHOD_scaleShapeProperty172].setDisplayName ( "" );
            methods[METHOD_scaleXProperty173] = new MethodDescriptor(javafx.scene.Node.class.getMethod("scaleXProperty", new Class[] {})); // NOI18N
            methods[METHOD_scaleXProperty173].setDisplayName ( "" );
            methods[METHOD_scaleYProperty174] = new MethodDescriptor(javafx.scene.Node.class.getMethod("scaleYProperty", new Class[] {})); // NOI18N
            methods[METHOD_scaleYProperty174].setDisplayName ( "" );
            methods[METHOD_scaleZProperty175] = new MethodDescriptor(javafx.scene.Node.class.getMethod("scaleZProperty", new Class[] {})); // NOI18N
            methods[METHOD_scaleZProperty175].setDisplayName ( "" );
            methods[METHOD_sceneProperty176] = new MethodDescriptor(javafx.scene.Node.class.getMethod("sceneProperty", new Class[] {})); // NOI18N
            methods[METHOD_sceneProperty176].setDisplayName ( "" );
            methods[METHOD_sceneToLocal177] = new MethodDescriptor(javafx.scene.Node.class.getMethod("sceneToLocal", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_sceneToLocal177].setDisplayName ( "" );
            methods[METHOD_sceneToLocal178] = new MethodDescriptor(javafx.scene.Node.class.getMethod("sceneToLocal", new Class[] {javafx.geometry.Point2D.class})); // NOI18N
            methods[METHOD_sceneToLocal178].setDisplayName ( "" );
            methods[METHOD_sceneToLocal179] = new MethodDescriptor(javafx.scene.Node.class.getMethod("sceneToLocal", new Class[] {javafx.geometry.Point3D.class})); // NOI18N
            methods[METHOD_sceneToLocal179].setDisplayName ( "" );
            methods[METHOD_sceneToLocal180] = new MethodDescriptor(javafx.scene.Node.class.getMethod("sceneToLocal", new Class[] {double.class, double.class, double.class})); // NOI18N
            methods[METHOD_sceneToLocal180].setDisplayName ( "" );
            methods[METHOD_sceneToLocal181] = new MethodDescriptor(javafx.scene.Node.class.getMethod("sceneToLocal", new Class[] {javafx.geometry.Bounds.class})); // NOI18N
            methods[METHOD_sceneToLocal181].setDisplayName ( "" );
            methods[METHOD_screenToLocal182] = new MethodDescriptor(javafx.scene.Node.class.getMethod("screenToLocal", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_screenToLocal182].setDisplayName ( "" );
            methods[METHOD_screenToLocal183] = new MethodDescriptor(javafx.scene.Node.class.getMethod("screenToLocal", new Class[] {javafx.geometry.Point2D.class})); // NOI18N
            methods[METHOD_screenToLocal183].setDisplayName ( "" );
            methods[METHOD_screenToLocal184] = new MethodDescriptor(javafx.scene.Node.class.getMethod("screenToLocal", new Class[] {javafx.geometry.Bounds.class})); // NOI18N
            methods[METHOD_screenToLocal184].setDisplayName ( "" );
            methods[METHOD_setAlignment185] = new MethodDescriptor(javafx.scene.layout.BorderPane.class.getMethod("setAlignment", new Class[] {javafx.scene.Node.class, javafx.geometry.Pos.class})); // NOI18N
            methods[METHOD_setAlignment185].setDisplayName ( "" );
            methods[METHOD_setMargin186] = new MethodDescriptor(javafx.scene.layout.BorderPane.class.getMethod("setMargin", new Class[] {javafx.scene.Node.class, javafx.geometry.Insets.class})); // NOI18N
            methods[METHOD_setMargin186].setDisplayName ( "" );
            methods[METHOD_setMaxSize187] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("setMaxSize", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_setMaxSize187].setDisplayName ( "" );
            methods[METHOD_setMinSize188] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("setMinSize", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_setMinSize188].setDisplayName ( "" );
            methods[METHOD_setPrefSize189] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("setPrefSize", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_setPrefSize189].setDisplayName ( "" );
            methods[METHOD_shapeProperty190] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("shapeProperty", new Class[] {})); // NOI18N
            methods[METHOD_shapeProperty190].setDisplayName ( "" );
            methods[METHOD_snappedBottomInset191] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("snappedBottomInset", new Class[] {})); // NOI18N
            methods[METHOD_snappedBottomInset191].setDisplayName ( "" );
            methods[METHOD_snappedLeftInset192] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("snappedLeftInset", new Class[] {})); // NOI18N
            methods[METHOD_snappedLeftInset192].setDisplayName ( "" );
            methods[METHOD_snappedRightInset193] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("snappedRightInset", new Class[] {})); // NOI18N
            methods[METHOD_snappedRightInset193].setDisplayName ( "" );
            methods[METHOD_snappedTopInset194] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("snappedTopInset", new Class[] {})); // NOI18N
            methods[METHOD_snappedTopInset194].setDisplayName ( "" );
            methods[METHOD_snapshot195] = new MethodDescriptor(javafx.scene.Node.class.getMethod("snapshot", new Class[] {javafx.scene.SnapshotParameters.class, javafx.scene.image.WritableImage.class})); // NOI18N
            methods[METHOD_snapshot195].setDisplayName ( "" );
            methods[METHOD_snapshot196] = new MethodDescriptor(javafx.scene.Node.class.getMethod("snapshot", new Class[] {javafx.util.Callback.class, javafx.scene.SnapshotParameters.class, javafx.scene.image.WritableImage.class})); // NOI18N
            methods[METHOD_snapshot196].setDisplayName ( "" );
            methods[METHOD_snapToPixelProperty197] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("snapToPixelProperty", new Class[] {})); // NOI18N
            methods[METHOD_snapToPixelProperty197].setDisplayName ( "" );
            methods[METHOD_startDragAndDrop198] = new MethodDescriptor(javafx.scene.Node.class.getMethod("startDragAndDrop", new Class[] {javafx.scene.input.TransferMode[].class})); // NOI18N
            methods[METHOD_startDragAndDrop198].setDisplayName ( "" );
            methods[METHOD_startFullDrag199] = new MethodDescriptor(javafx.scene.Node.class.getMethod("startFullDrag", new Class[] {})); // NOI18N
            methods[METHOD_startFullDrag199].setDisplayName ( "" );
            methods[METHOD_styleProperty200] = new MethodDescriptor(javafx.scene.Node.class.getMethod("styleProperty", new Class[] {})); // NOI18N
            methods[METHOD_styleProperty200].setDisplayName ( "" );
            methods[METHOD_toBack201] = new MethodDescriptor(javafx.scene.Node.class.getMethod("toBack", new Class[] {})); // NOI18N
            methods[METHOD_toBack201].setDisplayName ( "" );
            methods[METHOD_toFront202] = new MethodDescriptor(javafx.scene.Node.class.getMethod("toFront", new Class[] {})); // NOI18N
            methods[METHOD_toFront202].setDisplayName ( "" );
            methods[METHOD_topProperty203] = new MethodDescriptor(javafx.scene.layout.BorderPane.class.getMethod("topProperty", new Class[] {})); // NOI18N
            methods[METHOD_topProperty203].setDisplayName ( "" );
            methods[METHOD_toString204] = new MethodDescriptor(javafx.scene.Node.class.getMethod("toString", new Class[] {})); // NOI18N
            methods[METHOD_toString204].setDisplayName ( "" );
            methods[METHOD_translateXProperty205] = new MethodDescriptor(javafx.scene.Node.class.getMethod("translateXProperty", new Class[] {})); // NOI18N
            methods[METHOD_translateXProperty205].setDisplayName ( "" );
            methods[METHOD_translateYProperty206] = new MethodDescriptor(javafx.scene.Node.class.getMethod("translateYProperty", new Class[] {})); // NOI18N
            methods[METHOD_translateYProperty206].setDisplayName ( "" );
            methods[METHOD_translateZProperty207] = new MethodDescriptor(javafx.scene.Node.class.getMethod("translateZProperty", new Class[] {})); // NOI18N
            methods[METHOD_translateZProperty207].setDisplayName ( "" );
            methods[METHOD_usesMirroring208] = new MethodDescriptor(javafx.scene.Node.class.getMethod("usesMirroring", new Class[] {})); // NOI18N
            methods[METHOD_usesMirroring208].setDisplayName ( "" );
            methods[METHOD_visibleProperty209] = new MethodDescriptor(javafx.scene.Node.class.getMethod("visibleProperty", new Class[] {})); // NOI18N
            methods[METHOD_visibleProperty209].setDisplayName ( "" );
            methods[METHOD_widthProperty210] = new MethodDescriptor(javafx.scene.layout.Region.class.getMethod("widthProperty", new Class[] {})); // NOI18N
            methods[METHOD_widthProperty210].setDisplayName ( "" );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods
    // Here you can add code for customizing the methods array.

        return methods;     }//GEN-LAST:Methods

    private static java.awt.Image iconColor16 = null;//GEN-BEGIN:IconsDef
    private static java.awt.Image iconColor32 = null;
    private static java.awt.Image iconMono16 = null;
    private static java.awt.Image iconMono32 = null;//GEN-END:IconsDef
    private static String iconNameC16 = null;//GEN-BEGIN:Icons
    private static String iconNameC32 = null;
    private static String iconNameM16 = null;
    private static String iconNameM32 = null;//GEN-END:Icons

    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx


//GEN-FIRST:Superclass
    // Here you can add code for customizing the Superclass BeanInfo.

//GEN-LAST:Superclass
    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     *
     * @return BeanDescriptor describing the editable properties of this bean.
     * May return null if the information should be obtained by automatic
     * analysis.
     */
    @Override
    public BeanDescriptor getBeanDescriptor() {
        return getBdescriptor();
    }

    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     *
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean. May return null if the information
     * should be obtained by automatic analysis.
     * <p>
     * If a property is indexed, then its entry in the result array will belong
     * to the IndexedPropertyDescriptor subclass of PropertyDescriptor. A client
     * of getPropertyDescriptors can use "instanceof" to check if a given
     * PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return getPdescriptor();
    }

    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     *
     * @return An array of EventSetDescriptors describing the kinds of events
     * fired by this bean. May return null if the information should be obtained
     * by automatic analysis.
     */
    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        return getEdescriptor();
    }

    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     *
     * @return An array of MethodDescriptors describing the methods implemented
     * by this bean. May return null if the information should be obtained by
     * automatic analysis.
     */
    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        return getMdescriptor();
    }

    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are
     * customizing the bean.
     *
     * @return Index of default property in the PropertyDescriptor array
     * returned by getPropertyDescriptors.
     * <P>
     * Returns -1 if there is no default property.
     */
    @Override
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }

    /**
     * A bean may have a "default" event that is the event that will mostly
     * commonly be used by human's when using the bean.
     *
     * @return Index of default event in the EventSetDescriptor array returned
     * by getEventSetDescriptors.
     * <P>
     * Returns -1 if there is no default event.
     */
    @Override
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }

    /**
     * This method returns an image object that can be used to represent the
     * bean in toolboxes, toolbars, etc. Icon images will typically be GIFs, but
     * may in future include other formats.
     * <p>
     * Beans aren't required to provide icons and may return null from this
     * method.
     * <p>
     * There are four possible flavors of icons (16x16 color, 32x32 color, 16x16
     * mono, 32x32 mono). If a bean choses to only support a single icon we
     * recommend supporting 16x16 color.
     * <p>
     * We recommend that icons have a "transparent" background so they can be
     * rendered onto an existing background.
     *
     * @param iconKind The kind of icon requested. This should be one of the
     * constant values ICON_COLOR_16x16, ICON_COLOR_32x32, ICON_MONO_16x16, or
     * ICON_MONO_32x32.
     * @return An image object representing the requested icon. May return null
     * if no suitable icon is available.
     */
    @Override
    public java.awt.Image getIcon(int iconKind) {
        switch (iconKind) {
            case ICON_COLOR_16x16:
                if (iconNameC16 == null) {
                    return null;
                } else {
                    if (iconColor16 == null) {
                        iconColor16 = loadImage(iconNameC16);
                    }
                    return iconColor16;
                }
            case ICON_COLOR_32x32:
                if (iconNameC32 == null) {
                    return null;
                } else {
                    if (iconColor32 == null) {
                        iconColor32 = loadImage(iconNameC32);
                    }
                    return iconColor32;
                }
            case ICON_MONO_16x16:
                if (iconNameM16 == null) {
                    return null;
                } else {
                    if (iconMono16 == null) {
                        iconMono16 = loadImage(iconNameM16);
                    }
                    return iconMono16;
                }
            case ICON_MONO_32x32:
                if (iconNameM32 == null) {
                    return null;
                } else {
                    if (iconMono32 == null) {
                        iconMono32 = loadImage(iconNameM32);
                    }
                    return iconMono32;
                }
            default:
                return null;
        }
    }
    
}
