package reference;

public class Res {

	public int keyAddress;

	public Res(ReferenceTest a) {
		super();
		this.keyAddress = System.identityHashCode(a);
	}

	public void use(ReferenceTest a){
		if(System.identityHashCode(a) != keyAddress){
			throw new IllegalStateException();
		}
		else{
			System.out.println("use resource");
			for(int i = 0;i < 1000;i++){

			}
			System.out.println("resource usage is over");
		}
	}


	public void release(){
		System.out.println("resource is released");
	}
}
