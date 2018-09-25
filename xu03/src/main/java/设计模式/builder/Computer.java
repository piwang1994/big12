package 设计模式.builder;

/**
 * 电脑
 */
public class Computer {
	private String cpu;
	private String memory ;
	private String hardDisk ;

	public String getCpu() {
		return cpu;
	}

	private void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemory() {
		return memory;
	}

	private void setMemory(String memory) {
		this.memory = memory;
	}

	public String getHardDisk() {
		return hardDisk;
	}

	private void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}

	/**
	 * 内部构建器（静态内部类+链式编程的思想）
	 */
	static class Builder{

		//保留本类实例
		private Computer computer = new Computer() ;

		Builder setCpu(String cpu){
			computer.setCpu(cpu);
			return this ;
		}
		Builder setMemory(String mem){
			computer.setMemory(mem);
			return this ;
		}
		Builder setHardDisk(String disk){
			computer.setHardDisk(disk);
			return this ;
		}

		public Computer build(){
			return computer ;
		}
	}
}
