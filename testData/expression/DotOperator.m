hyp = hypSpace(h).extension;
T = 30;
assert(approxeq(hmmF.psi(:), dgm.CPDs{1}.T(:)));
assert(approxeq(hmmF.A, dgm.CPDs{2}.T));
assert(approxeq(hmmF.emission.T, dgm.localCPDs{1}.T));
dgm.CPDs{1}.prior = hmmF.piPrior(:);
dgm.CPDs{2}.prior = hmmF.transPrior;