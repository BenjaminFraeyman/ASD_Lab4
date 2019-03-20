package org.ibcn.gso.labo4.api;

public enum EffectType {
    SEPIA("Sepia"), INVERT("Invert"), HORIZONTAL_MIRROR("X Mirror"), VERTICAL_MIRROR("Y Mirror"), RGB_SHIFT("Shift RGB"), MOTION_BLUR("Motion Blur"), SHARPEN("Sharpen"), DETECT_EDGES("Detect Edges");

    private final String label;

    EffectType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
