package direction;

public enum Direction {
/*
 * 	define an enum class to record the direction of the robot and give some method to let the robot change its move direction
 */
	North,East,South,West;

/*
 *  a method to let the direction turn left
 */
	private Direction turnleft(){
		switch (this) {
		case North:
			return West;
		case East:
			return North;
		case South:
			return East;
		case West:
			return South;
		default:
			return South;
		}
	}

/*
 * 	four methods to use turn left several times to get the destination direction
 */
	public Direction back(){
		return this.turnleft().turnleft();
	}
	public Direction straight(){
		return this;
	}
	public Direction leftHandSide(){
		return this.turnleft();
	}
	public Direction rightHandSide(){
		return this.turnleft().turnleft().turnleft();
	}
	
}
