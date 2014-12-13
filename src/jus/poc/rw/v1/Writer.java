package jus.poc.rw.v1;

import jus.poc.rw.Actor;
import jus.poc.rw.Aleatory;
import jus.poc.rw.IResource;
import jus.poc.rw.control.IObservator;
import jus.poc.rw.deadlock.DeadLockException;

public class Writer extends Actor{

	public Writer(Aleatory useLaw, Aleatory vacationLaw, Aleatory iterationLaw,
			IResource[] selection, IObservator observator) {
		super(useLaw, vacationLaw, iterationLaw, selection, observator);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * Acquire the resource if no reader is currently using it
	 * @param resource the required resource
	 */
	protected void acquire(IResource resource) throws InterruptedException,
			DeadLockException {
		// TODO Auto-generated method stub
		System.out.println(this.getName()+" : demande d'écriture sur la ressource "+resource.ident());
		resource.beginW(this);
	}

	/**
	 * Release the resource after the writting is complete
	 * @param resource the resource which has been used
	 */
	@Override
	protected void release(IResource resource) throws InterruptedException {
		// TODO Auto-generated method stub
		resource.endW(this);
	}

}
