clear; clc; close('all');

%% Inputs:
%% Matrix
A = [
        7.35272     0.88255     -2.170052;
        0.88255     5.58351     0.528167;
        -2.27005    0.2    4.430329
     ];
 
 %% Right vector
 y = [
        1;
        0;
        0
     ];
%% Solve using LU defactorization
x = LUsystem(A,y);

disp('Answer:');
disp(x);

%% TODO: add square root method
x = A\y;

disp(x);

x = inv(A)*y;

disp(x);