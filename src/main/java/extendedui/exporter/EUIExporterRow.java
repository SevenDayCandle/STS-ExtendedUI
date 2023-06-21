package extendedui.exporter;

import extendedui.EUIUtils;

import java.lang.reflect.Field;

public class EUIExporterRow implements Comparable<EUIExporterRow> {
    public String ID;
    public String color;
    public String name;

    public EUIExporterRow(String id, String color, String name) {
        ID = id;
        this.color = color;
        this.name = name;
    }

    @Override
    public int compareTo(EUIExporterRow o) {
        return ID.compareTo(o.ID);
    }

    @Override
    public String toString() {
        // Ensure that the base fields come first
        return EUIUtils.joinStringsMapLists(",", field -> {
            try {
                return String.valueOf(field.get(this));
            }
            catch (IllegalAccessException e) {
                return "";
            }
        }, EUIExporterRow.class.getDeclaredFields(), this.getClass().getDeclaredFields()) + EUIExporter.NEWLINE;
    }

    public String getCsvHeaderRow() {
        return EUIUtils.joinStringsMapLists(",", Field::getName, EUIExporterRow.class.getDeclaredFields(), this.getClass().getDeclaredFields()) + EUIExporter.NEWLINE;
    }
}