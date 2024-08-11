public class LowestLoad implements Scheduler {
    private WorkerLoads workerLoads;

    public LowestLoad(WorkerLoads workerLoads) {
        this.workerLoads = workerLoads;
    }

    @Override
    public int schedule() {
        int low = workerLoads.getLowestLoad();
        System.out.println(low);
        return low;
    }
}
