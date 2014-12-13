package jus.poc.rw.v1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import jus.poc.rw.Actor;
import jus.poc.rw.Resource;
import jus.poc.rw.control.IObservator;
import jus.poc.rw.deadlock.DeadLockException;
import jus.poc.rw.deadlock.IDetector;

/**
 * Each instance of this class will have a lock that we can use later for the reading and the writting
 * @author Marie Darrigol
 *
 */
public class ResourceV1 extends Resource{

	private ReentrantReadWriteLock lock;
	
	public ResourceV1(IDetector detector, IObservator observator) {
		super(detector, observator);
		// TODO Auto-generated constructor stub
		lock = new ReentrantReadWriteLock();
	}

	@Override
	public void beginR(Actor arg0) throws InterruptedException,
			DeadLockException {
		// TODO Auto-generated method stub
		while(!lock.readLock().tryLock()){}
		System.out.println(arg0.getName()+" : ressource acquise pour lecture");
	}

	@Override
	public void beginW(Actor arg0) throws InterruptedException,
			DeadLockException {
		// TODO Auto-generated method stub
		while(!lock.writeLock().tryLock()){}
		System.out.println(arg0.getName()+" : ressource acquise pour écriture");
	}

	@Override
	public void endR(Actor arg0) throws InterruptedException {
		// TODO Auto-generated method stub
		if(lock.readLock().tryLock()){
			lock.readLock().unlock();
		}
		System.out.println(arg0.getName()+" : lecture terminée");
	}

	@Override
	public void endW(Actor arg0) throws InterruptedException {
		// TODO Auto-generated method stub
		lock.writeLock().unlock();
		System.out.println(arg0.getName()+ " : écriture terminée");
	}

	@Override
	public void init(Object arg0) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		// optionnal
	}

}
