package com.company.move;

import java.awt.*;

public class EnpassantMove extends SpecialMove {
    public EnpassantMove(Point start, Point end) {
        super(start, end, MoveType.ENPASSANT);
    }
}
