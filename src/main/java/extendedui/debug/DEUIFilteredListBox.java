package extendedui.debug;

import eatyourbeets.interfaces.delegates.FuncT1;
import imgui.ImGui;

import java.util.ArrayList;

import static extendedui.ui.EUIBase.Scale;

public class DEUIFilteredListBox<T> extends DEUIListBox<T>
{
    protected FuncT1<Boolean, T> evalFunc;

    public DEUIFilteredListBox(String id, ArrayList<T> items, FuncT1<String, T> stringFunc, FuncT1<Boolean, T> evalFunc)
    {
        this(id, items, stringFunc, evalFunc, -1, Scale(200));
    }

    public DEUIFilteredListBox(String id, ArrayList<T> items, FuncT1<String, T> stringFunc, FuncT1<Boolean, T> evalFunc, float width, float height)
    {
        super(id, items, stringFunc, width, height);
        this.evalFunc = evalFunc;
    }

    public void Render()
    {
        Render(width, height);
    }

    public void Render(float width, float height)
    {
        if (ImGui.beginListBox(ID, width, height)) {
            for (T item : items) {
                if (evalFunc.Invoke(item))
                {
                    boolean isSelected = item.equals(selected);
                    if (ImGui.selectable(stringFunc.Invoke(item), isSelected)) {
                        selected = item;
                    }

                    if (isSelected) {
                        ImGui.setItemDefaultFocus();
                    }
                }
            }
            ImGui.endListBox();
        }
    }
}
