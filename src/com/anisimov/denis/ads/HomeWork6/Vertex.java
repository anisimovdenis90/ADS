package com.anisimov.denis.ads.HomeWork6;

import java.util.Objects;

public class  Vertex {

    private final String label;
    private boolean visited;
    private Vertex prev;

    public Vertex(String label) {
        this.label = label;
    }

    public Vertex getPrev() {
        return prev;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }

    public boolean getVisited() {
        return visited;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + label + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(label, vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
