include  = @(d)addpath(genpathPMTK(d));
fitFn   = @(X)hmmFit(X, nstates, 'gauss', fitArgs{:});
logprobFn = @hmmLogprob;
fitFn   = @(X)hmmFit(X, nstates, 'mixGaussTied', fitArgs{:});
include  = @(d)addpath(genpathPMTK(d));