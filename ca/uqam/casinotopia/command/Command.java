package ca.uqam.casinotopia.command;

import java.io.ObjectOutputStream;

public interface Command {

	public void action();

	public void repondre(ObjectOutputStream oos);
}
