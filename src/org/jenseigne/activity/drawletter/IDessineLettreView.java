package org.jenseigne.activity.drawletter;

public interface IDessineLettreView {

	public abstract char getModelChar();

	public abstract float getCompleted();

	public abstract float getError();

	public abstract void nextLettre();

	public abstract void launchRetry();

}