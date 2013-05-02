package org.unice.m1.reseaux.sudoku.client.core;

/**
 * @author TEFFAHA Mortadha
 *
 * An Interface to be implemented by any class that wants to issue ActionPerformer events
 */
public interface ActionPerformerIssuer {
    void regiterActionPerformer(ActionPerformer performer);
    void unRegisterActionPerformer(ActionPerformer performer);
}
