
public class This implements Element {

	@Override
	public void accept(ReflectiveVisitor v) {
		v.visit( this );
	}
	public String thiss() { return "This"; }
}
