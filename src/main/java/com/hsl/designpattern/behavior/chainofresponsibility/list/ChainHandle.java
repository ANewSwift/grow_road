package com.hsl.designpattern.behavior.chainofresponsibility.list;

import java.util.ArrayList;
import java.util.List;

public class ChainHandle {
    private List<IHandle> handles = new ArrayList<>();

    public void addHandle(IHandle iHandle) {
        handles.add(iHandle);
    }

    public void handle() {
        for (IHandle handle : handles) {
            if (handle.handle()) {
                break;
            }
        }
    }
}
