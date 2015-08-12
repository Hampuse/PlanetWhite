package se.oakbright.battleobjects;

import java.util.LinkedList;

class ShipLauncher {
	private final int x;
	private final int y;
	private final int direction;
	private final BattleObject host;
	private Ship shipReadyToLaunch;
	private Ship newlyReleasedShip;
	private LinkedList<Ship> shipQ;
	
	public ShipLauncher(int x, int y, int direction, BattleObject host, LinkedList<Ship> shipQ) {
		this.x = x;
		this.y = y;
		this.direction = direction; //host.getSpeed.getDirectionDegBetween(host.getAnchorXworld(), host.getAnchorYworld(), x, y);
		this.host = host;
		if(shipQ != null){
			this.shipQ = new LinkedList<Ship>(shipQ);
		}
		else{
			this.shipQ = new LinkedList<Ship>(); //just create an empty list if the argument was null
		}
			//this.shipQ.add(new CrasherShip(host.getBattleSurface(),host.getTeam()));
			/*this.shipQ.add(new Ship(host.getBattleSurface(), x ,y , host.getMovingFrame(), host.getTeam(), host));
			this.shipQ.add(new Ship(host.getBattleSurface(), x ,y , host.getMovingFrame(), host.getTeam(), host));
			this.shipQ.add(new Ship(host.getBattleSurface(), x ,y , host.getMovingFrame(), host.getTeam(), host));
			this.shipQ.add(new Ship(host.getBattleSurface(), x ,y , host.getMovingFrame(), host.getTeam(), host));*/
		//}
		//shipReadyToLaunch = new Ship(host.getBattleSurface(), new Coord(x,y), host.getMovingFrame(), host.team, host);
		//shipReadyToLaunch.setSpeedLevel(0);
		
		//shipReadyToLaunch.
	}
	/*void setShipQ(LinkedList<Ship> shipQ){
		this.shipQ = shipQ;
	}*/
	
	void update(){
		if (this.shipReadyToLaunch != null){
			//this.shipReadyToLaunch.update(); //TODO SKA DEN VA HÄR
			if(this.shipReadyToLaunch.isLaunched()){
				//this.shipReadyToLaunch.setSpeedLevel(1);
				this.newlyReleasedShip = this.shipReadyToLaunch;
				this.shipReadyToLaunch = null;
			}
		}
		else{
			this.tryPrepareNextShipInQ();
		}
			
	}
	
	/*Ship pollNewlyReleasedShip(){
		Ship temp = this.newlyReleasedShip;
		this.newlyReleasedShip = null;
		return temp;
	}*/
	
	/*Ship getShipReadyToLaunch() {
		return shipReadyToLaunch;
	}*/
	
	void addShipToQ(Ship ship){
		this.shipQ.add(ship);
	}

	private void tryPrepareNextShipInQ() {	//TODO tidsrestriktion. omladdningstid
		this.shipReadyToLaunch = this.shipQ.poll();
		if(this.shipReadyToLaunch != null){
			// TODO? this.shipReadyToLaunch.halt(); //setSpeedLevel(0);
			//this.shipReadyToLaunch.initPosition(this.x, this.y, this.direction);
			this.shipReadyToLaunch.activate();
			//TODO? this.shipReadyToLaunch.commandGetReadyToLaunch(this.x, this.y, this.direction);
			
		}
	}
	
	
}
