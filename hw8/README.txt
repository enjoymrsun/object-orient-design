Class Animation:
This class stores all the shapes and its operations, we are allowed to edit it.


Interface Shape:
This interface represents a shape, we can get info related to a shape from it, including name, shape type, coordinates of its key point, width/height or radius, color, appear time and disappear time.

Abstract class AbstractShape:
This class implements Shape interface, it stores common methods for all the shapes including all the above except shape type.

Enum Class ShapeType:
Includes supported shape types.

Rectangle class:
This is a rectangle, returns Rectangle for getType().

Oval class:
This is an oval, returns Oval for getType().

... class:
other shapes.


Class Operation:
This class represents an operation, we can get info related to an operation from it, including name, operation type, operation start/end time, operation start/end value.

Enum Class OperationType:
Includes supported operation types.

AnimationTest:
Test illegal operations and implement text output.

问题：
1. 颜色全是绿色的，正常应该包含不同颜色，颜色读取的input有问题
2. 那些rectangle形状的0点 是左下角还是左上角 @param pos  bottom left corner coordinates
3. oval 里那个dur是随意设置的么？
4. operation好像没有严格按照时间排序