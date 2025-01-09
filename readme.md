
## Fundamental tools

### Chapter 1 Basic Concepts

- Concepts: state, action, reward, return, episode, policy
- Grid-world examples
- Markov decision process (MDP)
- Fundamental concepts, widely used later

[Chapter 1: Basic Concepts](https://www.notion.so/Chapter-1-Basic-Concepts-17440c5425ac8050a5afd4ddf390ce66?pvs=21)

### Chapter 2

- One concept: state value
- One tool: Bellman equation
- Policy evaluation, widely used later

[Chapter 2: Bellman Equation](https://www.notion.so/Chapter-2-Bellman-Equation-17440c5425ac80ada469d0855da1b8b2?pvs=21)

### Chapter 3

- A special Bellman equation
- Two concepts: optimal policy and optimal state value
- One tool: Bellman optimality equation
    - Fixed-point theorem
    - Fundamental problems
    - An algorithm solving the equation
- Optimality, widely used later

[Chapter 3. Optimal State Value and Bellman Optimality Equation](https://www.notion.so/Chapter-3-Optimal-State-Value-and-Bellman-Optimality-Equation-17540c5425ac80018e66dadce71322ad?pvs=21)

## Algorithms / Methods

### Chapter 4

- First algorithm for optimal policies
- Three algorithms:
    - Value iteration (VI)
    - Policy iteration (PI)
    - Truncated policy iteration
- Policy update and value update, widely used later
- Need the environment model

[Chapter 4 Value Iteration and Policy Iteration](https://www.notion.so/Chapter-4-Value-Iteration-and-Policy-Iteration-17640c5425ac8001b924fcc5845fd75a?pvs=21)

### Chapter 5

- Gap: how to do model-free learning
- Mean estimation with sampling data
- First model-free RL algorithms
- Algorithms:
    - MC Basic
    - MC Exploring Starts
    - MC e-greedy

### Chapter 6

- Gap: from non-incremental to incremental
- Mean estimation
- Algorithms:
    - Robbins-Monro (RM) algorithm
    - Stochastic gradient descent (SGD)
    - SGD, BGD, MBGD
- Incremental manner and SGD, widely used later

### Chapter 7

- Classic RL algoorithms
- Algorithms:
    - TD learning of state values
    - Sarsa: TD learning of action values
    - Q-learning: TD learning of optimal action values
        - on-policy & off-policy
    - Unified point of view

### Chapter 8

- Gap: tabular representation of function representation
- Algorithms:
    - State value estimation with value function approximation (VFA)
    - Sarsa with VFA
    - Q-learning with VFA
    - Deep Q-learning
- Neural network come into RL

### Chapter 9

- Gap: from value-based to policy-based
- Contents:
    - Metrics to define optimal policies
    - Policy gradient
    - Gradient-ascent algorithm

### Chapter 10

- Gap: policy-based + value-based
- Algorithms:
    - The simplest actor-critic (QAC)
    - Advantage actor-critic (A2C)
    - Off-policy actor-critic
        - Importance sampling
    - Deterministic actor-critic (DPG)