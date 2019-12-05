clear; clc; close('all');

%% Inputs:
% specify step
h = 0.001;

% specify boundaries
a = -1;
b = 1;

% specify amount of coordinate functions
n = 7;

%% Calculations:
% Using Galerkin method



figure;
fplot(y, [a b], 'b');
title('Result plot');
hold('on');
