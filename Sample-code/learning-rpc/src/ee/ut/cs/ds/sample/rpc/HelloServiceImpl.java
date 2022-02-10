package ee.ut.cs.ds.sample.rpc;

public class HelloServiceImpl implements HelloService {
	
	public String sayHello(String name) {
		return "Hello, " + name;
	}

}
