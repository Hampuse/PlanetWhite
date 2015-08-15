package se.oakbright.battleobjects;

import java.util.LinkedList;

class ShipLauncher {
	private final int x;
	private final int y;
	private final int direction;
	private final BattleObject host;
	private BattleObject<ShipCommands> shipReadyToLaunch;
	private BattleObject<ShipCommands> newlyReleasedShip;
	private LinkedList<BattleObject<ShipCommands>> shipQ;
	
	public ShipLauncher(int x, int y, int direction, BattleObject host, LinkedList<BattleObject<ShipCommands>> shipQ) {
		this.x = x;
		this.y = y;
		this.direction = direction; //host.getSpeed.getDirectionDegBetween(host.getAnchorXworld(), host.getAnchorYworld(), x, y);
		this.host = host;
		if(shipQ != null){
			this.shipQ = new LinkedList<BattleObject<ShipCommands>>(shipQ);
		}
		else{
			this.shipQ = new LinkedList<BattleObject<ShipCommands>>(); //just create an empty list if the argument was null
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
			if(this.shipReadyToLaunch.command().isLaunched()){
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
	
	void addShipToQ(BattleObject<ShipCommands> ship){
		this.shipQ.add(ship);
	}

	private void tryPrepareNextShipInQ() {	//TODO tidsrestriktion. omladdningstid
		this.shipReadyToLaunch = this.shipQ.poll();
		if(this.shipReadyToLaunch != null){
			// TODO? this.shipReadyToLaunch.halt(); //setSpeedLevel(0);
			//this.shipReadyToLaunch.initPosition(this.x, this.y, this.direction);
			this.shipReadyToLaunch.command().activate();
			//TODO? this.shipReadyToLaunch.commandGetReadyToLaunch(this.x, this.y, this.direction);
			
		}
	}
	
	
}
